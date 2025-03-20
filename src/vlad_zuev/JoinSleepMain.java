package vlad_zuev;

import java.util.Optional;
import java.util.stream.IntStream;

public class JoinSleepMain {
    private static final int firstNumber = 500_000;
    private static final int secondNumber = 1_000_000;

    private static int res1 = 0;
    private static int res2 = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < firstNumber; i++) {
                    res1++;
                }
                System.out.println(res1);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = firstNumber; i < secondNumber; i++) {
                    res2++;
                }
                System.out.println(res2);
            }
        });

//        Thread thread3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    thread.join();
//                    thread2.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        thread.start();
        thread2.start();
//        thread3.start();
//
//        thread3.join();


//        Thread.sleep(1);
        System.out.println(res1 + res2);
    }
}
