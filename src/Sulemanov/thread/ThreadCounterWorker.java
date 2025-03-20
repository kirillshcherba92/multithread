package Sulemanov.thread;

public class ThreadCounterWorker extends Thread {

    private final String name;
    private final int range;

    public ThreadCounterWorker(String name, int range) {
        this.name = name;
        this.range = range;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < range) {
            System.out.println(name + " " + count++);
        }
    }
}
