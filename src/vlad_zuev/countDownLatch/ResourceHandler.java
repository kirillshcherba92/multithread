package vlad_zuev.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class ResourceHandler extends ResourceTask {

    public ResourceHandler(long id, CountDownLatch countDownLatch) {
        super(id, countDownLatch);
    }

    @Override
    protected void run(CountDownLatch latch) {
        try {
            latch.await();
            System.out.printf("REsources were hendler by %s \n", this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
