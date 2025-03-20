package vlad_zuev.countDownLatch;

import java.util.concurrent.CountDownLatch;

public abstract class ResourcesTaskFactory {
    private long nextId;

    public final ResourceTask create(CountDownLatch countDownLatch) {
        return this.create(nextId++, countDownLatch);
    }

    protected abstract ResourceTask create(final long id, final CountDownLatch countDownLatch);
}
