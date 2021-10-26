package masterConcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lufengxiang
 * @since 2021/10/13
 **/
public class ThreadLocalTest implements Runnable {
    private Integer count = 0;
    // 一个普通的对象
    private NumberClass numberClass = new NumberClass();
    private ThreadLocal<NumberClass> threadLocal = new ThreadLocal();

    @Override
    public void run() {
        numberClass.setNum(++count);
        threadLocal.set(numberClass);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[Thread-" + Thread.currentThread().getId() + "]" + threadLocal.get().getNum());

    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ThreadLocalTest thread = new ThreadLocalTest();
        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(thread);
        }

        //                [Thread-12]5
        //                [Thread-15]5
        //                [Thread-14]5
        //                [Thread-11]5
        //                [Thread-13]5

        // 执行完5个线程中的threadLocal中的值相同
    }
}
