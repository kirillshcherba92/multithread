package Sulemanov.semaphore;

public class FooSemaphoreSafeDemo {
    public static void main(String[] args) {
        FooSemaphoreSafe fooSemaphoreSafe = new FooSemaphoreSafe();

        Thread thread1 = new Thread(fooSemaphoreSafe::first);
        Thread thread2 = new Thread(fooSemaphoreSafe::second);
        Thread thread3 = new Thread(fooSemaphoreSafe::third);

        thread1.setName("T1");
        thread2.setName("T2");
        thread3.setName("T3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
