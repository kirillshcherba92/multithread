package Sulemanov.concurency_collection;

import java.util.List;

public class ReadToArrayWorker implements Runnable {
    private final List<Integer> list;

    public ReadToArrayWorker(List<Integer> list) {
        this.list = list;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(150);
                System.out.println(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
