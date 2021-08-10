package cn.hzr0523.demo.mybatis;

import cn.hzr0523.DTO.UserDTO;
import cn.hzr0523.entity.TbUser;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * hezhi
 * 2018/11/9 16:48
 */
@RunWith(JUnit4.class)
@SpringBootTest
public class Demo {


    @Test
    public void test1() {
    }

    
    @Test
    public void test2() {
        Map<String, Object> params = new HashMap<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("111");
        userDTO.setPassword("222");
        userDTO.setEmail("333");

       params.put("a", "aaaaa");
        params.put("b", userDTO);



        String json = JSONObject.toJSONString(params);//map转json字符串
        System.out.println(json);
    }


    @Test
    public void test3() {

        ZonedDateTime now = ZonedDateTime.now();

        ZonedDateTime test = now.plusDays(1);

        long t1 = now.getMinute();
        System.out.println(t1);
        long t2 = test.getMinute();
        System.out.println(t2);

        System.out.println(t2 - t1);
    }

    @Test
    public void test4() {
        String s = "-100";
        int a = Integer.parseInt(s);
        System.out.println(a);
        if(a < 0) {
            System.out.println("q1111");
        }
    }
}
