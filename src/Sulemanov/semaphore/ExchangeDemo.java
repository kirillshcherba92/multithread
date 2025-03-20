package Sulemanov.semaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;

public class ExchangeDemo {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        AtomicInteger counter2 = new AtomicInteger();
        Exchanger<AtomicInteger> exchanger = new Exchanger<>();
        Exchanger<AtomicInteger> exchanger2 = new Exchanger<>();

        PingWorker pingWorker = new PingWorker(counter, exchanger);
        PongWorker pongWorker = new PongWorker(counter, exchanger);

        Thread thread1 = new Thread(pingWorker);
        Thread thread2 = new Thread(pongWorker);

        thread1.start();
        thread2.start();
    }
}
