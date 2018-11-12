package cn.hzr0523.demo.mybatis;

import cn.hzr0523.entity.TbUser;
import cn.hzr0523.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * hezhi
 * 2018/11/9 16:48
 */
@RunWith(JUnit4.class)
@SpringBootTest
public class Demo {
    @Autowired(required = false)
    UserMapper userMapper;

    @Test
    public void test1() {
       TbUser user = userMapper.getUserInfo("张三","123456");
       System.out.println(user.getUsername() + "," + user.getPhone());
    }
}
