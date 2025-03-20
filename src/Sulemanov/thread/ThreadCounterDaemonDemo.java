package Sulemanov.thread;

public class ThreadCounterDaemonDemo {
    public static void main(String[] args) {
        ThreadCounterWorkerDaemon threadCounterWorker1 = new ThreadCounterWorkerDaemon("A", 1000, true);
        ThreadCounterWorkerDaemon threadCounterWorker2 = new ThreadCounterWorkerDaemon("B", 100, false);

        threadCounterWorker1.start();
        threadCounterWorker2.start();

        System.out.println("Finish");
    }
}
