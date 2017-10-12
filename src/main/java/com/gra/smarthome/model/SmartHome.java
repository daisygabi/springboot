package com.gra.smarthome.model;

import java.util.List;

public class SmartHome {
    private long id;
    private String location;
    private List<Device> devices;
    private long adminGroupId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public long getAdminGroupId() {
        return adminGroupId;
    }

    public void setAdminGroupId(long adminGroupId) {
        this.adminGroupId = adminGroupId;
    }
}