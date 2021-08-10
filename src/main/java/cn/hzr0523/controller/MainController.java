package cn.hzr0523.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : hezr
 * @description :
 * @date : 2021/05/22
 **/
@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping("/toMain")
    public String toMain() {
        return "/business/main";
    }

    @RequestMapping("/toProduct")
    public String toProduct() {
        return "/business/product";
    }
}
