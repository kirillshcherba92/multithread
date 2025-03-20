package Sulemanov.concurency_collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);

        ReadFromQueueWorker readFromQueueWorker = new ReadFromQueueWorker(blockingQueue);
        WriteFromQueueWorker writeFromQueueWorker = new WriteFromQueueWorker(blockingQueue);

        Thread thread1 = new Thread(readFromQueueWorker);
        Thread thread2 = new Thread(writeFromQueueWorker);

        thread1.start();
        thread2.start();
    }
}
