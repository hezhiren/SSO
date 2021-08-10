package cn.hzr0523.controller;

import cn.hzr0523.service.ProductService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hezr
 * @description :
 * @date : 2021/05/30
 **/
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/productList")
    private JSONObject productList(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum,
                                   @RequestParam("pageSize") int pageSize) {
        log.info("请求入参：name:" + name + ", pageSize:" +pageSize + ", pageNum:" + pageNum);
        return getResult("100", "请求成功", productService.getProductList(name, pageNum, pageSize));
    }
}
