package cn.hzr0523.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * hezhi
 * 2018/5/3 11:39
 */
public class HttpUtil {

    /**
     * 解析post请求
     * @param servletRequest
     * @return
     */
    public static String postToString(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String line = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            //使用了设计模式之中的装饰者模式
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
