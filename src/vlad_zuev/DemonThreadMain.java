package vlad_zuev;

public class DemonThreadMain {
    private final static String FORMAT = "Thread Name is \"%s\": isDemon - %s";

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format(FORMAT, Thread.currentThread().getName(), Thread.currentThread().isDaemon()));

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("I working");
                System.out.println(String.format(FORMAT, Thread.currentThread().getName(), Thread.currentThread().isDaemon()));
                System.out.println();
            }
        });
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(7000);
        System.out.println(Thread.currentThread().getName() + " is stop !");
    }
}
