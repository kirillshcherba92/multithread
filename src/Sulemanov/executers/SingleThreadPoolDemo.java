package Sulemanov.executers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolDemo {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i < 100; i++) {
                executorService.submit(new GenerateRandomIntegerTask());
            }
        } finally {
            long timeEnd = System.currentTimeMillis();
            long duration = timeEnd - timeStart;
            System.out.println("Processed in: " + duration + " ms");
        }

    }
}
