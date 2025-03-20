package Sulemanov.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooSafe {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition firstMethodCondition = lock.newCondition();
    private final Condition secondMethodCondition = lock.newCondition();

    public void first() {
        lock.lock();
        System.out.println("first");
        firstMethodCondition.signalAll();
        lock.unlock();
    }

    public void second() {
        lock.lock();
        try {
            firstMethodCondition.await();
            System.out.println("second");
            secondMethodCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void third() {
        lock.lock();
        try {
            secondMethodCondition.await();
            System.out.println("third");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
