package cn.hzr0523.thread;

/**
 * 生产者
 * hezhi
 * 2018/10/25 21:01
 */
public class Producer implements Runnable {
    private Info info;

    public Producer(Info info) {
        this.info = info;
    }

    @Override
    public void run() {

        //设置标志位
        boolean flag = false;
        for(int i = 0; i < 20; i ++) {
            if(!flag) {
                this.info.setInfo("false", "false");
                flag = true;
            }else {
                this.info.setInfo("true", "true");
                flag = false;
            }

        }
    }
}
