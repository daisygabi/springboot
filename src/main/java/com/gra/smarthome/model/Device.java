package com.gra.smarthome.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "device")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceId")
    private Long deviceId;

    @Column(name="name")
    private String name;

    @Column(name="active")
    private boolean active;

    @Column(name="homeId")
    private long homeId;

    public Device(long deviceId, String name, boolean active) {
        this.deviceId = deviceId;
        this.name = name;
        this.active = active;
    }

    public Device(long homeId) {
        super();
        this.homeId = homeId;
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

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
    }
}