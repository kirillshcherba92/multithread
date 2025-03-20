package vlad_zuev;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CasMainAtomic {
    public static void main(String[] args) throws InterruptedException {
        EventNumberGenerator eventNumberGenerator = new EventNumberGenerator();

        final int taskGenCounts = 1000;
        Runnable runnable = () -> IntStream.range(0, taskGenCounts).forEach(value -> eventNumberGenerator.generate());

        Thread[] threads = createThreads(runnable, 5);
        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }

        System.out.println(eventNumberGenerator.getVal());

    }

    private static Thread[] createThreads(final Runnable runnable, final int amountOfThreads) {
        return IntStream.range(0, amountOfThreads)
                .mapToObj(value -> new Thread(runnable))
                .toArray(Thread[]::new);
    }
}

class EventNumberGenerator {
    private final static int GENERATION_DELTA = 2;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public int generate() {
        System.out.println(Thread.currentThread().getName() + " === " + atomicInteger.intValue());
        return atomicInteger.getAndAdd(GENERATION_DELTA);
    }

    public int getVal() {
        return atomicInteger.intValue();
    }
}