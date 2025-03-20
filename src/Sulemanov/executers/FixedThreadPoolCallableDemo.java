package Sulemanov.executers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolCallableDemo {
    public static void main(String[] args) {
        int core = Runtime.getRuntime().availableProcessors();

        List<Future<Integer>> futures = new ArrayList<>();
        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newFixedThreadPool(core * 100)) {
            for (int i = 0; i < 10_000; i++) {
                futures.add(executorService.submit(new GenerateRandomIntegerCallableTask()));
            }
        } finally {
            futures.forEach(integerFuture -> {
                try {
                    System.out.println(integerFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Time: " + duration);
        }
    }
}
