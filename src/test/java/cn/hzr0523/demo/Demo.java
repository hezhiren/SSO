package cn.hzr0523.demo;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * hezhi
 * 2018/6/12 13:19
 */
@RunWith(JUnit4.class)
public class Demo {
    private HashMap map = new HashMap();

    @Test
    public void test1() {
       boolean flag = true;
       if(!flag) {
           System.out.println(1);
       }
       System.out.println(!flag);
    }

    @Test
    public void test2() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap hashMap = new HashMap();
        Hashtable<String, Object> hashtable = new Hashtable<>();
        hashMap.put(null, "hello");
        System.out.println(hashMap.get(null));
    }


    @Test
    public void test3(){
        Integer[] array = new Integer[]{1,2,3,4};
        List<Integer> list = Arrays.asList(array);
    }

    //多线程就一定比单线程快？
    //测试一下

    @Test
    public void test4() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ofPattern("MM月dd日HH时mm分")));
    }

    @Test
    public void test5() {
      List<String> list = new ArrayList<>();
      list.add("1");
      list.add("2");
      list.add("3");
    }
}
