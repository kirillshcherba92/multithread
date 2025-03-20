package vlad_zuev;

public class PriorytiThreadMain {
    public static void main(String[] args) {
        printThreadPrio(Thread.currentThread());

        Thread thread = new Thread(() -> {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            printThreadPrio(Thread.currentThread());

            Thread thread1 = new Thread(() -> {
                printThreadPrio(Thread.currentThread());
            });
            thread1.start();

        });

        thread.start();
    }

    private static void printThreadPrio(Thread thread) {
        System.out.println(thread.getName() + " : " + thread.getPriority());
    }
}
