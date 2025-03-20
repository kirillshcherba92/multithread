package Sulemanov.synchro;

public class IncrementCounterZynchronizedDemoObject {
    static int counter = 0;
    static int otherCounter = 0;

    private static final Object counterLock = new Object();
    private static final Object otherCounterLock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                otherIncrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter : " + counter);
        System.out.println("otherCounter : " + otherCounter);
    }

    public static void increment() {
        synchronized (counterLock) {
            counter++;
        }
    }

    public static void otherIncrement() {
        synchronized (otherCounterLock) {
            otherCounter++;
        }
    }
}
