package vlad_zuev;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class ConditionMain {
    public static void main(String[] args) {
        BounderBuffer<Integer> integerBounderBuffer = new BounderBuffer<>(5);

        final Runnable runnable = () -> {
            Stream.iterate(0, integer -> integer + 1).forEach(integer -> {
                integerBounderBuffer.put(integer);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        };

        Thread thread1 = new Thread(runnable);

        final Runnable runnableGet = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                integerBounderBuffer.take();
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(runnableGet);

        thread1.start();
        thread2.start();


    }


}

class BounderBuffer<T> {
    private final T[] elements;
    private final Lock lock;
    private final Condition condition;
    private int size;

    public BounderBuffer(int size) {
        this.elements = (T[]) new Object[size];
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public boolean isFull() {
        lock.lock();
        try {
            return this.size == this.elements.length;
        } finally {
            lock.unlock();
        }

    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return this.size == 0;
        } finally {
            lock.unlock();
        }

    }

    public void put(T element) {
        lock.lock();
        try {
            while (this.isFull()) {
                condition.await();
            }

            this.elements[this.size] = element;
            this.size++;
            System.out.printf("%s was put in buffer. Result = %s \n", elements, this);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();
        try {
            while (this.isEmpty()) {
                condition.await();
            }
            T element = elements[size - 1];
            this.elements[this.size - 1] = null;
            size--;
            System.out.printf("%s was get from buffer. Result = %s \n", elements, this);
            condition.signalAll();
            return element;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


    @Override
    public String toString() {
        lock.lock();
        try {
            return "BounderBuffer{" +
                    "elements=" + Arrays.toString(elements) +
                    ", size=" + size +
                    '}';
        } finally {
            lock.unlock();
        }
    }
}