package Sulemanov.concurency_collection;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static List<Integer> integers = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        integers.addAll(Arrays.asList(100500, 200800, 100, 200, 300, 500));

        WriteToArrayWorker writeToArrayWorker = new WriteToArrayWorker(integers);
        ReadToArrayWorker readToArrayWorker = new ReadToArrayWorker(integers);

        Thread thread1 = new Thread(writeToArrayWorker);
        Thread thread2 = new Thread(readToArrayWorker);
        Thread thread3 = new Thread(writeToArrayWorker);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
