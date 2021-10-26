package masterDesign.rate;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lufengxiang
 * @since 2021/10/26
 **/
public class CounterLimiter {
    private int limit;//窗口内限流大小
    private AtomicInteger count;//当前窗口的计数器

    private CounterLimiter() {
    }

    public CounterLimiter(int windowSize, int limit) {
        this.limit = limit;
        //窗口大小，毫秒为单位
        count = new AtomicInteger(0);
        //开启一个线程，达到窗口结束时清空count
        new Thread(() -> {
            while (true) {
                count.set(0);
                try {
                    Thread.sleep(windowSize);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //请求到达后先调用本方法，若返回true，则请求通过，否则限流
    public boolean tryAcquire() {
        int newCount = count.addAndGet(1);
        return newCount <= limit;
    }

    //测试
    public static void main(String[] args) throws InterruptedException {
        //每秒20个请求
        CounterLimiter counterLimiter = new CounterLimiter(1000, 20);
        int count = 0;
        //模拟50次请求，看多少能通过
        for (int i = 0; i < 50; i++) {
            if (counterLimiter.tryAcquire()) {
                count++;
            }
        }
        System.out.println("第一拨50次请求中通过：" + count + ",限流：" + (50 - count));
        //过一秒再请求
        Thread.sleep(1000);
        //模拟50次请求，看多少能通过
        count = 0;
        for (int i = 0; i < 50; i++) {
            if (counterLimiter.tryAcquire()) {
                count++;
            }
        }
        System.out.println("第二拨50次请求中通过：" + count + ",限流：" + (50 - count));
    }
}
