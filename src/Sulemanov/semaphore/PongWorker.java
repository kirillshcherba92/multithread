package Sulemanov.semaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;

public class PongWorker implements Runnable {
    private final AtomicInteger counter;
    private final Exchanger<AtomicInteger> exchanger;

    public PongWorker(AtomicInteger counter, Exchanger<AtomicInteger> exchanger) {
        this.counter = counter;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                AtomicInteger exchange = exchanger.exchange(counter);
                System.out.println("PONG: " + exchange.getAndIncrement());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
