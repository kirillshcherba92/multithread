package Sulemanov.reentrantlock;

public class FooUnsafeDemo {
    public static void main(String[] args) {
        final FooUnSafe fooUnSafe = new FooUnSafe();
        Thread thread1 = new Thread(fooUnSafe::first);
        Thread thread2 = new Thread(fooUnSafe::second);
        Thread thread3 = new Thread(fooUnSafe::third);

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
