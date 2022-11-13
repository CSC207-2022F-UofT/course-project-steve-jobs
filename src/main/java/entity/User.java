package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<String> connectionRequests;
    private ArrayList<String> pendingConnections;
    private ArrayList<String> connections;
    private String id;
    private String name;
    private int access_level;
    public User() {}
    public User(String id,
                ArrayList<String> connectionRequests,
                ArrayList<String> pendingConnections,
                ArrayList<String> connections,
                String name,
                int access_level) {
        this.connectionRequests = connectionRequests;
        this.pendingConnections = pendingConnections;
        this.connections = connections;
        this.id = id;
        this.name = name;
        this.access_level = access_level;
    }

    public ArrayList<String> getConnectionRequests() {
        return connectionRequests;
    }

    public void setConnectionRequests(ArrayList<String> connectionRequests) {
        this.connectionRequests = connectionRequests;
    }

    public ArrayList<String> getPendingConnections() {
        return pendingConnections;
    }

    public void setPendingConnections(ArrayList<String> pendingConnections) {
        this.pendingConnections = pendingConnections;
    }

    public ArrayList<String> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<String> connections) {
        this.connections = connections;
    }
    public String getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getaccesslevel(){return access_level;}
}