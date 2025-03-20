package vlad_zuev.cocleBarrier;

import java.util.List;

public class SubTask extends CompositeTask<LeafTask> {

    public SubTask(final long id,
                   final List<LeafTask> leafTasks
    ) {
        super(id, leafTasks);
    }

    @Override
    protected void perform(final LeafTask leafTask) {
        leafTask.perform();
    }

}
