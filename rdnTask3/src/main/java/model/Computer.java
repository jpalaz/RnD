package model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Computer {

    private String name;
    private String ip;
    private String userGroup;
    private String os;
    private String cabinet;
    private final Set<Connection> connections;

    public Computer(String name, String ip, String userGroup, String os, String cabinet) {
        this.name = name;
        this.ip = ip;
        this.userGroup = userGroup;
        this.os = os;
        this.cabinet = cabinet;
        this.connections = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public String getOs() {
        return os;
    }

    public String getCabinet() {
        return cabinet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        if (name != null ? !name.equals(computer.name) : computer.name != null) return false;
        if (ip != null ? !ip.equals(computer.ip) : computer.ip != null) return false;
        if (userGroup != null ? !userGroup.equals(computer.userGroup) : computer.userGroup != null) return false;
        if (os != null ? !os.equals(computer.os) : computer.os != null) return false;
        return cabinet != null ? cabinet.equals(computer.cabinet) : computer.cabinet == null;
    }

    public boolean addConnection(Connection connection) {
        boolean isAdded;
        synchronized (connections) {
            isAdded = connections.add(connection);
            notifyAll();
        }
        return isAdded;
    }

    public boolean removeConnection(Connection connection) throws InterruptedException {
        boolean isRemoved;
        synchronized (connections) {
            isRemoved = connections.remove(connection);
            notifyAll();
        }
        return isRemoved;
    }

    public void closeConnections() throws InterruptedException {
        for (Connection connection : connections) {
            removeOrphanConnection(connection.getFirst(), connection);
            removeOrphanConnection(connection.getSecond(), connection);
            connection.close();
        }
    }

    private void removeOrphanConnection(Computer computer, Connection connection) throws InterruptedException {
        if (computer != this) {
            computer.removeConnection(connection);
        }
    }

    public boolean isNotConnectedTo(Computer computer) {
        synchronized (connections) {
            for (Connection connection : connections) {
                if (isConnected(computer, connection)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isConnected(Computer computer, Connection connection) {
        return (this.equals(connection.getFirst()) && computer.equals(connection.getSecond()))
                || (computer.equals(connection.getFirst()) && this.equals(connection.getSecond()));
    }

    public boolean match(Computer params) {
        if ((this.cabinet.equals(params.getCabinet()) || params.getCabinet() == null)
                && (this.name.equals(params.getName()) || params.getName() == null)
            && (this.os.equals(params.getOs()) || params.getOs() == null)
            && (this.ip.equals(params.getIp()) || params.getIp() == null)
            && (this.userGroup.equals(params.getUserGroup()) || params.getUserGroup() == null)) {
            return true;
        }
        return false;
    }
}
