package vlad_zuev;

import java.util.stream.IntStream;

public class ThreadStateMain {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                IntStream.rangeClosed(1, 1_000_000_000).boxed().reduce(Integer::sum).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("LolThread");


        printThread(thread);
        printThread(Thread.currentThread());
        thread.start();
        Thread.sleep(1000);
        printThread(thread);
        printThread(Thread.currentThread());
        Thread.sleep(1005);
        printThread(thread);
        printThread(Thread.currentThread());
        thread.join();
        printThread(thread);
        printThread(Thread.currentThread());

//        thread.start();
    }

    private static void printThread(Thread thread) {
        System.out.println(String.format("%s: %s", thread.getName(), thread.getState()));
    }
}
