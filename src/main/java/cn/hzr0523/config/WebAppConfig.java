package cn.hzr0523.config;

import cn.hzr0523.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 * WebMvcConfigurerAdapter在spring boot 2.0，Spring 5.0 以后WebMvcConfigurerAdapter会取消掉
 * hezhi
 * 2018/5/17 11:08
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    //由于拦截器加载的时间点在springcontext之前
    @Bean
    public HandlerInterceptor getLoginInterceptor() {
        return new UserLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/user/**").excludePathPatterns("/user/toLogin.do");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("*/toIndex.do").setViewName("/index/index.html");
        registry.addViewController("*/toLogin.do").setViewName("/user/login.html");
    }
}
