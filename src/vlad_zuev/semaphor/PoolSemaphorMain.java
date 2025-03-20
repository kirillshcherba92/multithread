package vlad_zuev.semaphor;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PoolSemaphorMain {
    public static void main(String[] args) {
        int poolSize = 3;

        ConnectionPool pool = new ConnectionPool(poolSize);
        ConnectionPoolTask connectionPoolTask = new ConnectionPoolTask(pool);

        Thread[] threads = THreadUtil.createThreads(connectionPoolTask, 15);
        THreadUtil.startThreads(threads);
    }

}


