package cn.hzr0523.thread;

/**
 * hezhi
 * 2018/10/31 20:16
 */
public class Test {
    public static void main(String[] args) {
        //创建并启动线程
        new Thread(){
            public void run() {
                for(int i = 0; i < 50; i ++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "------" + i);
                }
            }
        }.start();
        new Thread(){
            public void run() {
                for(int i = 0; i < 50; i ++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "------" + i);
                }
            }
        }.start();
    }
}
