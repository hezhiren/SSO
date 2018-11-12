package cn.hzr0523.thread;

/**
 * 消费者
 * hezhi
 * 2018/10/25 21:01
 */
public class Consumer implements Runnable{
    private Info info;

    public Consumer(Info info) {
        this.info = info;
    }

    @Override
    public void run() {
        for(int i = 0; i < 20; i++) {
            this.info.getInfo();
        }
    }
}
