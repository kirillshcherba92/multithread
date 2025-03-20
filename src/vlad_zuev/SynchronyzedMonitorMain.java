package vlad_zuev;

public class SynchronyzedMonitorMain {
    private static int firstCounter = 0;
    private static int secondCounter = 0;

    private static Object monitorForFirstCounter = new Object();
    private static Object monitorForSecondCounter = new Object();

    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();


        System.out.println("start firstCounter = " + counter1.getCounter());
        System.out.println("start secondCounter = " + counter2.getCounter());
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
//                counter1.incrementCounter();
                counter1.incrementCounter(monitorForFirstCounter);
            }
        });

        Thread thread11 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
//                counter1.incrementCounter();
                counter1.incrementCounter(monitorForFirstCounter);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
//                counter2.incrementCounter();
                counter2.incrementCounter(monitorForSecondCounter);
            }
        });
        Thread thread22 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
//                counter2.incrementCounter();
                counter2.incrementCounter(monitorForSecondCounter);
            }
        });

        thread1.start();
        thread11.start();
        thread2.start();
        thread22.start();

        thread1.join();
        thread11.join();
        thread2.join();
        thread22.join();
        System.out.println("end firstCounter = " + counter1.getCounter());
        System.out.println("end secondCounter = " + counter2.getCounter());
    }

    public static void incrementFirstCounter() {
        synchronized (monitorForFirstCounter) {
            firstCounter++;
        }
    }

    public static void incrementSecondCounter() {
        synchronized (monitorForSecondCounter) {
            secondCounter++;
        }
    }
}

class Counter {
    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public synchronized void incrementCounter() {
        counter++;
    }

    public void incrementCounter(final Object monitor) {
        synchronized (monitor) {
            counter++;
        }
    }
}
