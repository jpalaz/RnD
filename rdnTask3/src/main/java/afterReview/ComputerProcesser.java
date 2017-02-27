package afterReview;

import java.util.Set;
import java.util.function.Predicate;

public class ComputerProcesser {
    private Computer computer;

    public ComputerProcesser(Computer computer) {
        this.computer = computer;
    }

    public boolean addConnection(Computer computer) {
        Set<Computer> connections = computer.getConnections();
        return connections.add(computer);
    }

    public boolean removeConnection(Computer computer) {
        Set<Computer> connections = computer.getConnections();
        return connections.remove(computer);
    }
}
