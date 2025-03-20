package Sulemanov.concurency_collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionUnSafeDemo {
    public static List<Integer> treadUnsafeList = new ArrayList<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_00; i++) {
                treadUnsafeList.add(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_00; i++) {
                treadUnsafeList.add(i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(treadUnsafeList.size());
    }

}
