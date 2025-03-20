package vlad_zuev.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class ResourceHandlerFactory extends ResourcesTaskFactory {
    @Override
    protected ResourceHandler create(long id, CountDownLatch countDownLatch) {
        return new ResourceHandler(id, countDownLatch);
    }
}
