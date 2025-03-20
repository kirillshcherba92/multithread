package Sulemanov.thread;

public record RunnableCounterWorker(String name, int range) implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (count < range) {
            System.out.println(name + " ::: " + count++);
        }
    }
}
