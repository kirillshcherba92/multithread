package vlad_zuev;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionMain {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500_000; i++) {
                counter++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500_000; i++) {
                counter++;
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 500_000; i++) {
                counter++;
            }
        });

        System.out.println("Start counter = " + counter);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Finish counter = " + counter);
    }

}
