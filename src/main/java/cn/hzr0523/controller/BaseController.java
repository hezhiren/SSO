package cn.hzr0523.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hezhi
 * 2018/5/3 13:26
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected JSONObject getResult(String code, String msg, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);
        return  jsonObject;
    }
}
