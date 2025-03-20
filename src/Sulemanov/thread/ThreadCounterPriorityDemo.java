package Sulemanov.thread;

public class ThreadCounterPriorityDemo {
    public static void main(String[] args) {
        ThreadCounterWorkerPriority threadCounterWorker1 = new ThreadCounterWorkerPriority("A", 20, 10);
        ThreadCounterWorkerPriority threadCounterWorker2 = new ThreadCounterWorkerPriority("B", 20, 1);

        threadCounterWorker1.start();
        threadCounterWorker2.start();

        System.out.println("Finish");
    }
}
