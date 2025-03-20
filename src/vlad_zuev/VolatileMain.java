package vlad_zuev;

public class VolatileMain {
    private static boolean temp = true;
    private static int start = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            while (temp) {
                System.out.println(Thread.currentThread().getName() + " lol 1 is ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (temp) {
                System.out.println(Thread.currentThread().getName() + " lol 2 is ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        temp = false;
        Thread.sleep(2000);
    }


}
