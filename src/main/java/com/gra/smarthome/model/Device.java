package com.gra.smarthome.model;

import javax.persistence.*;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deviceId;

    @Column(name="name")
    private String name;

    @Column(name="active")
    private boolean active;

    public Device(long deviceId, String name, boolean active) {
        this.deviceId = deviceId;
        this.name = name;
        this.active = active;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}