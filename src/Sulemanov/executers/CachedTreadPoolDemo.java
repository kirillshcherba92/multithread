package Sulemanov.executers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedTreadPoolDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 100; i++) {
                executorService.submit(new GenerateRandomIntegerTask());
            }
            System.out.println("TEST");
        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Time: " + duration);
        }
    }
}
