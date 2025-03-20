package Sulemanov.concurency_collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        WriteToMapWorker writeToMapWorker = new WriteToMapWorker(concurrentMap);
        ReaderToMapWorker readerToMapWorker = new ReaderToMapWorker(concurrentMap);

        Thread thread1 = new Thread(writeToMapWorker);
        Thread thread2 = new Thread(writeToMapWorker);
        Thread thread3 = new Thread(readerToMapWorker);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
