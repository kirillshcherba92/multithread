package vlad_zuev;

public class StopThreadMain {

    private static volatile boolean isRunningServer = true;

    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(() -> {
            while (isRunningServer) {
                if (isRunningServer) {
                    System.out.println("Req to send");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isRunningServer) {
                    System.out.println("Response is recieved");
                }
                System.out.println();
            }
        });

        thread.start();

        final Thread stopThread = new Thread(() -> {
            isRunningServer = false;
            System.out.println(thread.getName() + " is stopped");
        });

        Thread.sleep(5000);
        stopThread.start();
    }
}
