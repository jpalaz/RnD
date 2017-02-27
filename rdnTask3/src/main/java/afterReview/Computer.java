package afterReview;

import java.util.LinkedHashSet;
import java.util.Set;

public class Computer {
    private String name;
    private String ip;
    private String userGroup;
    private String os;
    private String cabinet;
    private final Set<Computer> connections;

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

    public Set<Computer> getConnections() {
        return connections;
    }
}
