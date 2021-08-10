package cn.hzr0523.service;

import cn.hzr0523.DTO.ResultMsg;

/**
 * @author : hezr
 * @description :
 * @date : 2021/05/30
 **/
public interface ProductService {

    ResultMsg getProductList(String name, int pageNum, int pageSize);
}
