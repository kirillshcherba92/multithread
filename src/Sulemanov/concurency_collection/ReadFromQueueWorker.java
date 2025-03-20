package Sulemanov.concurency_collection;

import java.util.concurrent.BlockingQueue;

public class ReadFromQueueWorker implements Runnable {
    private final BlockingQueue<Integer> queue;

    public ReadFromQueueWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer take = queue.take();
                System.out.println("Take: " + take);
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
