package vlad_zuev;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTaskMAin {
    public static void main(String[] args) {
        final Lock firstGivenLock = new ReentrantLock();
        final Lock secondGivenLock = new ReentrantLock();

        final Thread firstGivenThread = new Thread(new Task(firstGivenLock, secondGivenLock));
        final Thread secondGivenThread = new Thread(new Task(secondGivenLock, firstGivenLock));

        firstGivenThread.start();
        secondGivenThread.start();
    }
}


class Task implements Runnable {
    private static final String A = "THread %s is trying to acquire lock '%s' \n";
    private static final String B = "THread %s acquired lock '%s' \n";
    private static final String C = "THread %s released lock '%s' \n";

    private static final String NAME_FIRST_LOCK = "firstLock";
    private static final String NAME_SECOND_LOCK = "secondLock";

    private final Lock lock1;
    private final Lock lock2;

    Task(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        final String currentThreadName = Thread.currentThread().getName();
        System.out.printf(A, currentThreadName, NAME_FIRST_LOCK);
        this.lock1.lock();
        try {
            System.out.printf(B, currentThreadName, NAME_FIRST_LOCK);
            Thread.sleep(200);
            System.out.printf(A, currentThreadName, NAME_SECOND_LOCK);
            lock2.lock();
            try {
                System.out.printf(B, currentThreadName, NAME_SECOND_LOCK);
            } finally {
                lock2.unlock();
                System.out.printf(C, currentThreadName, NAME_SECOND_LOCK);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
            System.out.printf(C, currentThreadName, NAME_FIRST_LOCK);
        }
    }
}