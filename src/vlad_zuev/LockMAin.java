package vlad_zuev;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockMAin {
    private static int count = 0;
    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; i++) {
                    genearate();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(count);
    }

    private static int genearate() {
        lock.lock();
        try {
            return count += 2;
        } finally {
            lock.unlock();
        }

    }
}
