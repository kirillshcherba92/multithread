package Sulemanov.concurency_collection;

import java.util.List;
import java.util.Random;

public class WriteToArrayWorker implements Runnable {
    private final List<Integer> list;
    private final Random random = new Random();

    public WriteToArrayWorker(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                this.list.set(random.nextInt(list.size()), random.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
