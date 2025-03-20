package vlad_zuev.cocleBarrier;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class MAin {
    public static void main(String[] args) {
        final int subtaskCountInMainTask = 2;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(
                subtaskCountInMainTask,
                () -> System.out.println("****************************")
        );

        final LeafTask firstLeafTask = new LeafTask(0, 5, cyclicBarrier);
        final LeafTask secondLeafTask = new LeafTask(1, 3, cyclicBarrier);
        final LeafTask thirdLeafTask = new LeafTask(2, 1, cyclicBarrier);
        final SubTask firstSubtask = new SubTask(0, List.of(firstLeafTask, secondLeafTask, thirdLeafTask));

        final LeafTask fourthLeafTask = new LeafTask(3, 6, cyclicBarrier);
        final LeafTask fifthLeafTask = new LeafTask(4, 4, cyclicBarrier);
        final LeafTask sixthLeafTask = new LeafTask(5, 2, cyclicBarrier);
        final SubTask secondSubtask = new SubTask(1, List.of(fourthLeafTask, fifthLeafTask, sixthLeafTask));

        final MainTask mainTask = new MainTask(0, List.of(firstSubtask, secondSubtask));

        mainTask.perform();
    }
}
