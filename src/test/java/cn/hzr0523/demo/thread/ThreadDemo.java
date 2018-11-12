package cn.hzr0523.demo.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
/**
 * hezhi
 * 2018/11/8 15:35
 */
@RunWith(JUnit4.class)
public class ThreadDemo {
    @Test
    public void test1() {
       int count  = 1000;
       concurrent(count);
       serial(count);
    }

    public void concurrent(int count) {
        Long start = System.nanoTime();
        new Thread(()->{
            for(int i = 0; i < count; i ++) {
                int a = 0;
                a += 1;
                System.out.println(Thread.currentThread().getName() + ": a=" + a);

            }
        }).start();
        Long end = System.nanoTime();
        System.out.println("并发耗时：" + (end -start));
    }

    public void serial(int count) {
        Long start = System.nanoTime();
        for(int i = 0; i < count; i ++) {
            int a = 0;
            a += 1;
            System.out.println(Thread.currentThread().getName() + ": a=" + a);
        }
        Long end = System.nanoTime();
        System.out.println("串行运行耗时：" + (end - start));
    }
}
