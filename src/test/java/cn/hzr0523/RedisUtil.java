package cn.hzr0523;

import cn.hzr0523.service.JedisClient;
import cn.hzr0523.util.RedisClusterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author : hezr
 * @description :
 * @date : 2021/04/19
 **/
@RunWith(JUnit4.class)
@SpringBootTest
public class RedisUtil {

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void testInsert() {
        RedisClusterUtil.setMapField("111","222","333");
        Map<Object, Object> map = RedisClusterUtil.getMapAllField("111");
        jedisClient.set("test0419", "test0419");
        System.out.printf(map.toString());
        System.out.printf(jedisClient.get("test0419"));
    }
}
