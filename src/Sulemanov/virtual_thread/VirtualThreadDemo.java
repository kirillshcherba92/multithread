package Sulemanov.virtual_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadDemo {
    public static void main(String[] args) {
        List<GenerateRandomNumberTask> generateRandomNumberTasks = generateTask();
        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            generateRandomNumberTasks.forEach(executorService::submit);
        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Time: " + duration);
        }
    }

    private static List<GenerateRandomNumberTask> generateTask() {
        List<GenerateRandomNumberTask> generateRandomNumberTasks = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            generateRandomNumberTasks.add(new GenerateRandomNumberTask());
        }
        return generateRandomNumberTasks;
    }
}
