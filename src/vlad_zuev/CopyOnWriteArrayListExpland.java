package vlad_zuev;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExpland {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        integers.forEach(System.out::print);
        integers.set(1, 0);
        System.out.println();
        integers.forEach(System.out::print);
        integers.add(9);
        System.out.println();
        integers.forEach(System.out::print);
    }

}
