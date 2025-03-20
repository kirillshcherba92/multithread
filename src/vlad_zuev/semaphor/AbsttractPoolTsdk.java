package vlad_zuev.semaphor;

public abstract class AbsttractPoolTsdk<T> implements Runnable {

    private final AbstractPool<T> pool;

    public AbsttractPoolTsdk(AbstractPool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        final T t = this.pool.acquire();
        handle(t);
        try {
            System.out.println(String.format("%s was  acquire", t));

        } finally {
            this.pool.release(t);
            System.out.println(String.format("%s realesed ", t));
        }
    }

    protected abstract void handle(final T object);
}
