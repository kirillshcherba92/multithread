package vlad_zuev;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class ConditionMainPart2 {
    public static void main(String[] args) {
        BounderBufferPart2<Integer> integerBounderBufferPart2 = new BounderBufferPart2<>(5);

        final Runnable runnable = () -> {
            Stream.iterate(0, integer -> integer + 1).forEach(integer -> {
                integerBounderBufferPart2.put(integer);
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
                integerBounderBufferPart2.take();
                try {
                    Thread.sleep(2000);
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

class BounderBufferPart2<T> {
    private final T[] elements;
    private final Lock lock;
    private final Condition isFull;
    private final Condition isEmpty;
    private int size;

    public BounderBufferPart2(int size) {
        this.elements = (T[]) new Object[size];
        lock = new ReentrantLock();
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();
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
                isFull.await();
            }

            this.elements[this.size] = element;
            this.size++;
            System.out.printf("%s was put in buffer. Result = %s \n", elements, this);
            isEmpty.signal();
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
                isEmpty.await();
            }
            T element = elements[size - 1];
            this.elements[this.size - 1] = null;
            size--;
            System.out.printf("%s was get from buffer. Result = %s \n", elements, this);
            isFull.signal();
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