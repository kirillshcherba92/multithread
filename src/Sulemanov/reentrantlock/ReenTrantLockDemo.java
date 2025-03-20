package Sulemanov.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockDemo {
    public static int count = 0;
    public static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                incrementCounter();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                incrementCounter();
            }
        });
        long timeStart = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Count = " + count);
        long timeEnd = System.currentTimeMillis();
        System.out.println("Duration: " + (timeEnd - timeStart));

    }


//    static public void incrementCounter() {
//        lock.lock();
//        count++;
//        lock.unlock();
//    }

    public static synchronized void incrementCounter() {
        count++;
    }
}
