package vlad_zuev;

public class SynchroRaceConditionMain {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start counter = " + counter);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                incrementMyCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                incrementMyCount();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("end counter = " + counter);
    }

    public static synchronized void incrementMyCount() {
        counter++;
    }
}
