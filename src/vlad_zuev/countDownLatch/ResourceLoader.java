package vlad_zuev.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class ResourceLoader extends ResourceTask {

    long secondDuration;

    public ResourceLoader(long id, CountDownLatch countDownLatch, long secondDuration) {
        super(id, countDownLatch);
        this.secondDuration = secondDuration;
    }

    @Override
    protected void run(CountDownLatch latch) {
        try {
            System.out.printf("%s is loading some resource \n", this);
            Thread.sleep(this.secondDuration * 1000);
            System.out.printf("Some resource was loaded by %s\n", this);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
