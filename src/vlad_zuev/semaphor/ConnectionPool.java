package vlad_zuev.semaphor;

import java.util.function.Supplier;

public class ConnectionPool extends AbstractPool<Connection> {

    public ConnectionPool(int size) {
        super(new ConnectionSupplier(), size);
    }

    @Override
    public void cleanObject(final Connection connection) {
        connection.setAutoCommit(false);
    }

    private static final class ConnectionSupplier implements Supplier<Connection> {

        long nextConnectionId;

        @Override
        public Connection get() {
            return new Connection(this.nextConnectionId++, true);
        }
    }
}
