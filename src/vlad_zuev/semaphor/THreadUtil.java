package vlad_zuev.semaphor;

import java.util.Arrays;
import java.util.stream.IntStream;

public class THreadUtil {

    public static Thread[] createThreads(Runnable runnable, int mountOfThread) {
        return IntStream.range(0, mountOfThread)
                .mapToObj(value -> new Thread(runnable))
                .toArray(Thread[]::new);
    }

    public static void startThreads(Thread[] threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }

}
