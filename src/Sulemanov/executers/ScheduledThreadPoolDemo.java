package Sulemanov.executers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        long timeStart = System.currentTimeMillis();

        try (ScheduledExecutorService executorService = Executors.newScheduledThreadPool(cores - 1)) {
            GeneratedRandomIntegerWithIdTask task1 = new GeneratedRandomIntegerWithIdTask(1);
            GeneratedRandomIntegerWithIdTask task2 = new GeneratedRandomIntegerWithIdTask(2);
            GeneratedRandomIntegerWithIdTask task3 = new GeneratedRandomIntegerWithIdTask(3);
            GeneratedRandomIntegerWithIdTask task4 = new GeneratedRandomIntegerWithIdTask(4);
            GeneratedRandomIntegerWithIdTask task5 = new GeneratedRandomIntegerWithIdTask(5);

//            executorService.schedule(task2, 10, TimeUnit.SECONDS);
//            executorService.schedule(task1, 3, TimeUnit.SECONDS);
//            executorService.schedule(task3, 2, TimeUnit.SECONDS);
//            executorService.schedule(task4, 1, TimeUnit.SECONDS);
//            executorService.schedule(task5, 0, TimeUnit.SECONDS);

        } finally {
            long timeEnd = System.currentTimeMillis();
            long duration = timeEnd - timeStart;
            System.out.println("Processed in: " + duration + " ms");
        }

    }
}
