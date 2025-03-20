package vlad_zuev.semaphor;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;
import java.util.stream.IntStream;

abstract class AbstractPool<K> {

    private final List<PoolObject<K>> poolObjects;
    private final Semaphore semaphore;

    public AbstractPool(final Supplier<K> supplier, int size) {
        poolObjects = IntStream.range(0, size)
                .mapToObj(value -> supplier.get())
                .map(k -> new PoolObject<K>(k, false))
                .toList();
        semaphore = new Semaphore(size);
    }

    public final K acquire() {
        this.semaphore.acquireUninterruptibly();
        synchronized (this) {
            return poolObjects.stream()
                    .filter(kPoolObject -> !kPoolObject.isIssued())
                    .findFirst()
                    .map(this::markAsIssurd)
                    .map(PoolObject::getObject)
                    .orElseThrow(IllegalStateException::new);
        }
    }

    public void release(final K object) {
        if (this.releaseObject(object)) {
            this.semaphore.release();
        }
    }

    private synchronized boolean releaseObject(K object) {
        return this.poolObjects.stream()
                .filter(kPoolObject -> kPoolObject.isIssued())
                .filter(kPoolObject -> Objects.equals(kPoolObject.getObject(), object))
                .findFirst()
                .map(this::cleanPoolObject)
                .isPresent();
    }

    public abstract void cleanObject(final K object);

    private PoolObject<K> cleanPoolObject(final PoolObject<K> poolObject) {
        poolObject.setIssued(false);
        this.cleanObject(poolObject.getObject());
        return poolObject;
    }

    private <K> PoolObject<K> markAsIssurd(final PoolObject<K> kPoolObject) {
        kPoolObject.setIssued(true);
        return kPoolObject;
    }

    private static final class PoolObject<T> {
        private final T object;
        private boolean issued;

        public PoolObject(T object, boolean issued) {
            this.object = object;
            this.issued = issued;
        }

        public T getObject() {
            return object;
        }

        public boolean isIssued() {
            return issued;
        }

        public void setIssued(boolean issued) {
            this.issued = issued;
        }
    }
}