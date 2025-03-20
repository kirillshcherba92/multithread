package Sulemanov.thread;

public class ThreadCounterWorkerPriority extends Thread {

    private final String name;
    private final int range;
    private final int priority;

    public ThreadCounterWorkerPriority(String name, int range, int priority) {
        this.name = name;
        this.range = range;
        this.priority = priority;
        super.setPriority(this.priority);
    }

    @Override
    public void run() {
        int count = 0;
        while (count < range) {
            System.out.println(name + " " + count++);
        }
    }
}
