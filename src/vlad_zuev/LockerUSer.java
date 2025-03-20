package vlad_zuev;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class LockerUSer {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        int incrementAmount = 10;
        Thread thread1 = new Thread(createTask(counter, value -> counter.increment(), incrementAmount));
        Thread thread2 = new Thread(createTask(counter, value -> counter.decrement(), incrementAmount));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("=======================");
        System.out.println(counter.value);
    }

    private static Runnable createTask(Counter counter, IntConsumer intConsumer, int times) {
        Lock lock = new ReentrantLock();
        return () -> {
            counter.setLock();
//            lock.lock();
            try {
                IntStream.range(0, times).forEach(intConsumer);
            } finally {
                counter.unLock();
//                lock.unlock();
            }
        };
    }

    private static class Counter {
        private final Lock lock = new ReentrantLock();
        private int value;

        public void setLock() {
            this.lock.lock();
            currnetThread("Thread %s locked counter.\n");
        }

        private static void currnetThread(String message) {
            System.out.printf(message, Thread.currentThread().getName());
        }

        public void increment() {
            this.value++;
            currnetThread("Thread %s incremeted counter.\n");
        }

        public void decrement() {
            this.value--;
            currnetThread("Thread %s decremented counter.\n");
        }

        public void unLock() {
            currnetThread("Thread %s unlocked counter.\n");
            this.lock.unlock();
        }

        public int getValue() {
            return value;
        }
    }
}
