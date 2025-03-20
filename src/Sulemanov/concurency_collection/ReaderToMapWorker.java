package Sulemanov.concurency_collection;

import java.util.concurrent.ConcurrentMap;

public class ReaderToMapWorker implements Runnable {
    private final ConcurrentMap<String, Integer> concurrentMap;

    public ReaderToMapWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(10);
            System.out.println("A: " + concurrentMap.get("A"));
            Thread.sleep(10);
            System.out.println("B: " + concurrentMap.get("B"));
            Thread.sleep(5000);
            System.out.println("C: " + concurrentMap.get("C"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
