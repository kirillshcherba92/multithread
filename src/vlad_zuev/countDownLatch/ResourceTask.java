package vlad_zuev.countDownLatch;

import java.util.concurrent.CountDownLatch;

public abstract class ResourceTask implements Runnable {

    private final long id;
    private final CountDownLatch countDownLatch;

    public ResourceTask(long id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        run(countDownLatch);
    }

    protected abstract void run(final CountDownLatch latch);

    @Override
    public String toString() {
        return "ResourceTask{" +
                "id=" + id +
                ", countDownLatch=" + countDownLatch +
                '}';
    }
}
