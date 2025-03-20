package vlad_zuev;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class REantWriteReadLockMAin {
    public static void main(String[] args) {
        MyVal myVal = new MyVal(1);
        Thread threadRead = new Thread(new THreadReadRun(2000, myVal));
        Thread threadRead1 = new Thread(new THreadReadRun(2000, myVal));
        Thread threadRead2 = new Thread(new THreadReadRun(2000, myVal));
        Thread threadRead3 = new Thread(new THreadReadRun(2000, myVal));
        Thread threadRead4 = new Thread(new THreadReadRun(2000, myVal));

        Thread threadWrite = new Thread(new THreadWriteRun(1000, myVal));
        Thread threadWrite1 = new Thread(new THreadWriteRun(1000, myVal));

        threadRead.start();
        threadRead1.start();
        threadRead2.start();
        threadRead3.start();
        threadRead4.start();

        threadWrite.start();
        threadWrite1.start();
    }
}

class THreadReadRun implements Runnable {

    private final int countToSleep;
    private final MyVal myVal;

    public THreadReadRun(int countToSleep,
                         MyVal myVal) {
        this.countToSleep = countToSleep;
        this.myVal = myVal;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + " val = " + myVal.getVal());
                Thread.sleep(this.countToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class THreadWriteRun implements Runnable {

    private final int countToSleep;
    private final MyVal myVal;

    public THreadWriteRun(int countToSleep,
                          MyVal myVal) {
        this.countToSleep = countToSleep;
        this.myVal = myVal;
    }

    @Override
    public void run() {
        while (true) {
            try {
                myVal.increment();
                System.out.println(Thread.currentThread().getName() + " increment myVal");
                Thread.sleep(this.countToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyVal {
    private int val;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock;
    private final Lock writeLock;

    public MyVal(int val) {
        this.val = val;
        readLock = reentrantReadWriteLock.readLock();
        writeLock = reentrantReadWriteLock.writeLock();
    }

    public int getVal() {
        try {
            readLock.lock();
            return val;
        } finally {
            readLock.unlock();
        }
    }

    public void increment() {
        try {
            writeLock.lock();
            this.val++;
        } finally {
            writeLock.unlock();
        }
    }
}

















