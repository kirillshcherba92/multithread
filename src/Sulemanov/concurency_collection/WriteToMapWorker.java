package Sulemanov.concurency_collection;

import java.util.concurrent.ConcurrentMap;

public class WriteToMapWorker implements Runnable {
    private final ConcurrentMap<String, Integer> concurrentMap;

    public WriteToMapWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }


    @Override
    public void run() {
        try {
            concurrentMap.put("A", 1);
            Thread.sleep(100);
            concurrentMap.put("B", 2);
            Thread.sleep(100);
            concurrentMap.put("C", 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
