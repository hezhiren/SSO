package cn.hzr0523.controller;

import cn.hzr0523.DTO.UserDTO;
import cn.hzr0523.VO.ResultObject;
import cn.hzr0523.config.BaseController;
import cn.hzr0523.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * hezhi
 * 2018/5/2 14:51
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

//    @RequestMapping
//    public String defaultPage() {
//        return "/user/login.html";
//    }
//
//    @RequestMapping("/toLogin.do")
//    public String login() {
//        return "/user/login.html";
//    }

    @RequestMapping("/toRegister.do")
    public String toRegister() {
        return "/user/register.html";
    }

    @ResponseBody
    @RequestMapping("/register.do")
    public JSONObject userRegister(@RequestBody UserDTO userDTO) {
        ResultObject resultObject = null;
        resultObject = userService.userRegister(userDTO);
        return getResult(resultObject.getResultCode(), resultObject.getResultMessage(), resultObject.getResultData());
    }

    @ResponseBody
    @RequestMapping("/login.do")
    public JSONObject userLogin(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        ResultObject resultObject = null;
        resultObject = userService.userLogin(userDTO, request, response);
        return getResult(resultObject.getResultCode(), resultObject.getResultMessage(), resultObject.getResultData());
    }

    @RequestMapping("/test.do")
    public JSONObject toIndex() {
        ResultObject resultObject = null;
        resultObject = userService.getUserInfo("张三","123456");
        return getResult(resultObject.getResultCode(), resultObject.getResultMessage(), resultObject.getResultData());
    }
}