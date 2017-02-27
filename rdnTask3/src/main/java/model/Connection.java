package model;

public class Connection {
    private Computer first;
    private Computer second;
    private boolean isLive;

    public Connection(Computer first, Computer second) {
        this.first = first;
        this.first.addConnection(this);
        this.second = second;
        this.second.addConnection(this);
        this.isLive = true;
    }

    public void close() {
        this.isLive = false;
    }

    public Computer getFirst() {
        return first;
    }

    public Computer getSecond() {
        return second;
    }

    public boolean isLive() {
        return isLive;
    }
}
