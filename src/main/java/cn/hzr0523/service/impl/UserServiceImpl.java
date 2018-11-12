package cn.hzr0523.service.impl;

import cn.hzr0523.DTO.UserDTO;
import cn.hzr0523.VO.ResultObject;
import cn.hzr0523.entity.TbUser;
import cn.hzr0523.mapper.UserMapper;
import cn.hzr0523.service.IUserService;
import cn.hzr0523.service.JedisClient;
import cn.hzr0523.util.CookieUtils;
import cn.hzr0523.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * hezhi
 * 2018/5/2 13:51
 */
@Service
public class UserServiceImpl implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = false)
    private UserMapper userMapper;

    //注入不成功，需要修改redis的config文件，
    @Autowired
    private JedisClient jedisClient;

    @Value("${USER_SESSION_KEY}")
    private String USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public ResultObject userRegister(UserDTO userDTO) {
        ResultObject resultObject = new ResultObject();
        if (StringUtil.isEmpty(userDTO.getEmail()) || StringUtil.isEmpty(userDTO.getUserName()) || StringUtil.isEmpty(userDTO.getPassword())) {
            resultObject.setResultCode("0");
            resultObject.setResultMessage("参数错误");
            return resultObject;
        }
        try {
            TbUser user = new TbUser();
            user.setCreated(new Date());
            user.setEmail(userDTO.getEmail());
            user.setUsername(userDTO.getUserName());
            user.setPassword(userDTO.getPassword());
            userMapper.insert(user);
            resultObject.setResultCode("1");
            resultObject.setResultMessage("成功");
        } catch (Exception e) {
            logger.info(e.getMessage());
            resultObject.setResultCode("0");
            resultObject.setResultMessage("注册失败");
        }
        return resultObject;
    }

    @Override
    public ResultObject userLogin(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        ResultObject resultObject = new ResultObject();
        if (userDTO == null) {
            resultObject.setResultCode("0");
            resultObject.setResultMessage("参数错误");
            return resultObject;
        }
        //根据登录信息查询学员信息
        TbUser user = userMapper.getUserInfo(userDTO.getUserName(), userDTO.getPassword());
        if (user == null) {
            resultObject.setResultCode("0");
            resultObject.setResultMessage("用户名或密码错误");
            return resultObject;
        }
        //登录成功，把用户信息写入redis
        //String s = jedisClientSingle.get(USER_SESSION_KEY);
        //logger.info("USER_SESSION_KEY: " + s);
        //生成一个用户token
//        String token = UUID.randomUUID().toString();
//        logger.info(JSONObject.toJSONString(user));
//        jedisClient.set(USER_SESSION_KEY + ":" + token, JSONObject.toJSONString(user));
//        //设置session过期时间
//        jedisClient.expire(USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
//        //添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
//        CookieUtils.setCookie(response, "TT_TOKEN", token, -1);
        //返回token
        resultObject.setResultCode("1");
        resultObject.setResultMessage("登录成功");
//        resultObject.setResultData(token);
//        byte[] bytes = JSONObject.toJSONBytes(resultObject);
//        System.out.println(JSONObject.toJSONBytes(resultObject));
//        try {
//            System.out.println(new String(bytes, "utf-8"));
//            JSONObject json = JSONObject.parseObject(new String(bytes, "utf-8"));
//            System.out.println(json);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("ahah");
        //logger.info();
        return resultObject;
    }

    @Override
    public TbUser getUserByToken(String token) {
        String json = jedisClient.get(USER_SESSION_KEY + ":" + token);
        if (StringUtil.isEmpty(json)) {
            return null;
        }
        return JSONObject.toJavaObject(JSONObject.parseObject(json), TbUser.class);
    }

    @Override
    public ResultObject getUserInfo(String name, String pwd) {
        ResultObject resultObject = new ResultObject();
        TbUser user = userMapper.getUserInfo(name, pwd);
        resultObject.setResultCode("1");
        resultObject.setResultMessage("登录成功");
        return resultObject;
    }
}
