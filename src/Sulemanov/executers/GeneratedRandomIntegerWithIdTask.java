package Sulemanov.executers;

import java.util.Random;

public class GeneratedRandomIntegerWithIdTask implements Runnable {
    private final Random random = new Random();
    private final Integer id;

    public GeneratedRandomIntegerWithIdTask(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        int randomInt = random.nextInt(1000) + 1;
        System.out.println("SingleThreadPoolTask: " + id + ", value = " + randomInt);
    }
}
