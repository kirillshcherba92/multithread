package vlad_zuev.countDownLatch;

import vlad_zuev.semaphor.THreadUtil;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CountDownLatchMain {
    public static void main(String[] args) {
        int resourcesCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(resourcesCount);
        Thread[] resourcesThread = createResourcesThread(new ResourceLoaderFactory(), resourcesCount, countDownLatch);

        int resourcesHeadlerCount = 4;
        Thread[] resourcesHandlerThread = createResourcesThread(new ResourceHandlerFactory(), resourcesHeadlerCount, countDownLatch);

        THreadUtil.startThreads(resourcesThread);
        THreadUtil.startThreads(resourcesHandlerThread);
    }


    private static Thread[] createResourcesThread(final ResourcesTaskFactory factory, final int threadCount, CountDownLatch countDownLatch) {
        return IntStream.range(0, threadCount)
                .mapToObj(value -> factory.create(countDownLatch))
                .map(Thread::new)
                .toArray(Thread[]::new);
    }
}
