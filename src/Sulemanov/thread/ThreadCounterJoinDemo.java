package Sulemanov.thread;

public class ThreadCounterJoinDemo {
    public static void main(String[] args) {
        ThreadCounterWorker threadCounterWorker1 = new ThreadCounterWorker("A", 15);
        ThreadCounterWorker threadCounterWorker2 = new ThreadCounterWorker("B", 100);

        threadCounterWorker1.start();
        threadCounterWorker2.start();

        try {
            threadCounterWorker1.join();
            threadCounterWorker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finish");
    }
}
