package vlad_zuev;

public class UncaughtExceptionMain {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                throw new RuntimeException("Шеф, всё пропало !!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                throw new RuntimeException("Шеф, всё пропало2222 !!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                throw new RuntimeException("Ou, NoNoNo !!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        MyUncaughtExc myUncaughtExc = new MyUncaughtExc();
        thread1.setUncaughtExceptionHandler(myUncaughtExc);
        thread2.setUncaughtExceptionHandler(myUncaughtExc);

        GlobalMyUncaughtExc globalMyUncaughtExc = new GlobalMyUncaughtExc();
        Thread.setDefaultUncaughtExceptionHandler(globalMyUncaughtExc);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class MyUncaughtExc implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Мое исключение::::" + " " + e.getMessage() + "threadName = " + t.getName());
    }
}

class GlobalMyUncaughtExc implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("My Global exception::::" + " " + e.getMessage() + "threadName = " + t.getName());
    }
}