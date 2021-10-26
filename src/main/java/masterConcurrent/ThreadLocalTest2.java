package masterConcurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lufengxiang
 * @since 2021/10/13
 **/
public class ThreadLocalTest2 implements Runnable {

    private final ThreadLocal<List<Long>> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        List<Long> list = threadLocal.get();
        if (list == null) {
            list = new ArrayList<>();
            System.out.println("[Thread-" + Thread.currentThread().getId() + "]  init threadLocal");
        }
        list.add((long) (1 + Math.random() * (10)));
        threadLocal.set(list);
        List<Long> list2 = threadLocal.get();
        System.out.println("[Thread-a-" + Thread.currentThread().getId() + "]" + list2);
        System.out.println("[Thread-b-" + Thread.currentThread().getId() + "]" + list);
    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        ThreadLocalTest2 thread = new ThreadLocalTest2();
        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(thread);
        }
    }
}
