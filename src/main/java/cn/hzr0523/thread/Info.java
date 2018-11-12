package cn.hzr0523.thread;

/**
 * 生产者-消费者模型
 * hezhi
 * 2018/10/25 21:00
 */
public class Info {

    private String topic;

    private String content;

    //设置标志位，false表示可以取走，但不能生产，此时生产者进入等待，
    // true表示可以生产，但不能取走，此时消费者进入等待状态。
    private boolean flag = true;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public synchronized void setInfo(String topic, String content) {
        if(!flag) {
            //flag = false，可以取走，不能生产，生产者等待
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.topic = topic;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
        //生产完了，设置为false
        flag = false;//改变标志位，表示可以取走
        super.notify();
    }

    public synchronized void getInfo() {
        if(flag) {
            //flag为true，只能生产，不能取走，消费者进入等待状态
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getTopic() + "," + this.getContent());
        //消费完了，再去生产
        flag = true;
        super.notify();
    }
}
