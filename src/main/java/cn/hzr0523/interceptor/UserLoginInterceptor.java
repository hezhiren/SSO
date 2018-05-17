package cn.hzr0523.interceptor;

import cn.hzr0523.entity.TbUser;
import cn.hzr0523.service.IUserService;
import cn.hzr0523.util.CookieUtils;
import cn.hzr0523.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * hezhi
 * 2018/5/14 16:55
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户token
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if(StringUtil.isEmpty(token)) {
            //用户未登陆
            //跳转到登录页面
            response.sendRedirect("http://localhost:8080/toLogin.do");
            return false;
        }else {
            //如果能取到token说明用户可能已经登录
            //从SSO中获取用户信息，判断用户是否登录
            TbUser user = userService.getUserByToken(token);
            if(user == null) {
                //session过期，重新登录，跳转到登录页面
                response.sendRedirect("http://localhost:8080/toLogin.do");
                return false;
            }else {
                //将用户信息你放入request中
                request.setAttribute("user", user);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
