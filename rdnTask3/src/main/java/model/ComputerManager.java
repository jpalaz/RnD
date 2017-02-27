package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Model of some building with computers. Some of them connected (one-to-one)
 */
public class ComputerManager extends Thread {

    /**
     * We can't create 2 same computers in a building, so it's a Set.
     * Java Best practices (http://www.javapractices.com/topic/TopicAction.do?Id=65) says, that
     * the best general purpose implementation is likely LinkedHashSet.
     * I have not any special purpose in my case, so I chose it.
     */
    private final Set<Computer> computers = new LinkedHashSet<>();

    public Computer addComputer(String name, String ip, String userGroup, String os, String cabinet) {
        Computer computer = new Computer(name, ip, userGroup, os, cabinet);
        synchronized (computers) {
            computers.add(computer);
        }
        return computer;
    }

    public void deleteComputer(Computer computer) throws InterruptedException {
        computer.closeConnections();
    }

    public Connection addConnection(Computer first, Computer second) {
        if (first.isNotConnectedTo(second)) {
            return new Connection(first, second);
        }
        throw new IllegalArgumentException("Connection already exists");
    }

    public void deleteConnection(Connection connection) throws InterruptedException {
        connection.close();
        Computer computer = connection.getFirst();
        computer.removeConnection(connection);
        computer = connection.getSecond();
        computer.removeConnection(connection);
    }

    /**
     * @param params is computer object of nullable parameters. If all not null params match some computer, it's returned
     * @return list of found devices
     */
    public List<Computer> findComputers(Computer params) {
        ArrayList<Computer> found = new ArrayList<>();
        synchronized (computers) {
            for (Computer computer : computers) {
                if (computer.match(params)) {
                    found.add(computer);
                }
            }
        }
        return found;
    }
}
