package Sulemanov.reentrantlock;

public class FooSafeDemo {
    public static void main(String[] args) {
        FooSafe fooSafe = new FooSafe();
        Thread thread1 = new Thread(fooSafe::first);
        Thread thread2 = new Thread(fooSafe::second);
        Thread thread3 = new Thread(fooSafe::third);

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
