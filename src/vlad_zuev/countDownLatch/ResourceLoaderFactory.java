package vlad_zuev.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class ResourceLoaderFactory extends ResourcesTaskFactory {

    private final long nestSecondDur = 1;

    @Override
    protected ResourceLoader create(long id, CountDownLatch countDownLatch) {
        return new ResourceLoader(id, countDownLatch, nestSecondDur);
    }
}
