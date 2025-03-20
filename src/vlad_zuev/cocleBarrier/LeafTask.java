package vlad_zuev.cocleBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.currentThread;

public final class LeafTask extends Task{

    private final long secondDuration;
    private final CyclicBarrier cyclicBarrier;

    public LeafTask(final long id,
                    final long secondDuration,
                    final CyclicBarrier cyclicBarrier
    ) {
        super(id);
        this.secondDuration = secondDuration * 1000;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void perform() {
        try {
            System.out.printf("%s is starting\n", this);
            Thread.sleep(this.secondDuration);
            System.out.printf("%s has finished\n", this);
            this.cyclicBarrier.await();
        } catch (final InterruptedException exception) {
            currentThread().interrupt();
        } catch (final BrokenBarrierException cause) {
            throw new RuntimeException(cause);
        }
    }
}
