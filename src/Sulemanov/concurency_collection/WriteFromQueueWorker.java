package Sulemanov.concurency_collection;

import java.util.concurrent.BlockingQueue;

public class WriteFromQueueWorker implements Runnable {
    private final BlockingQueue<Integer> queue;

    public WriteFromQueueWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                System.out.println("Set: " + counter);
                queue.add(counter++);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
