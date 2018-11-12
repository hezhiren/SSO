package cn.hzr0523.thread;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;

import java.util.concurrent.*;

/**
 * hezhi
 * 2018/10/31 11:30
 */
public class ThreadDemo{

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //创建线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 20; i ++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "------ run");
            });
        }

        //submit方法调用结束后，程序并不终止，是因为线程池控制了线程的关闭。将使用完的线程又归还到了线程池中
        //关闭线程池
        //executorService.shutdown();
    }
}

class ThreadCallable implements Callable<Integer>{
    private int result = 0;

    @Override
    public Integer call() throws Exception {
        for(int i = 0; i < 100; i ++) {
            try{
                Thread.sleep(100);
            }catch (InterruptedException e) {

            }
            result += 1;
            System.out.println(Thread.currentThread().getName() + "----" + result);
        }
        return result;
    }
}
