package Sulemanov.executers;

import java.util.Random;
import java.util.concurrent.Callable;

public class GenerateRandomIntegerCallableTask implements Callable<Integer> {

    private final Random random = new Random();

    @Override
    public Integer call() {
        Integer res = null;
        try {
            Thread.sleep(100);
            res = random.nextInt(1000) + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }
}
