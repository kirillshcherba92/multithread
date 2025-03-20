package vlad_zuev.semaphor;

public class ConnectionPoolTask extends AbsttractPoolTsdk<Connection> {

    public ConnectionPoolTask(AbstractPool pool) {
        super(pool);
    }

    @Override
    protected void handle(Connection connection) {
        try {
            connection.setAutoCommit(true);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
