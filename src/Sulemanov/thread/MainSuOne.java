package Sulemanov.thread;

public class MainSuOne {
    public static void main(String[] args) {
//        ThreadCounterWorker threadCounterWorker1 = new ThreadCounterWorker("A", 1000);
//        ThreadCounterWorker threadCounterWorker2 = new ThreadCounterWorker("B", 1000);
//
//        threadCounterWorker1.start();
//        threadCounterWorker2.start();

        ///////////////////////////////////////////////

        RunnableCounterWorker runnableCounterWorker1 = new RunnableCounterWorker("AA", 20);
        RunnableCounterWorker runnableCounterWorker2 = new RunnableCounterWorker("BB", 20);

        Thread thread1 = new Thread(runnableCounterWorker1);
        Thread thread2 = new Thread(runnableCounterWorker2);

        thread1.start();
        thread2.start();

        /////////////////////////////////////////////////


    }
}
