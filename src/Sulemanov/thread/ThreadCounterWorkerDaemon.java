package Sulemanov.thread;

public class ThreadCounterWorkerDaemon extends Thread {

    private final String name;
    private final int range;
    private final boolean isDaemon;

    public ThreadCounterWorkerDaemon(String name, int range, boolean isDaemon) {
        this.name = name;
        this.range = range;
        this.isDaemon = isDaemon;
        super.setDaemon(this.isDaemon);
    }

    @Override
    public void run() {
        int count = 0;
        while (count < range) {
            System.out.println(name + " " + count++);
        }
    }
}
