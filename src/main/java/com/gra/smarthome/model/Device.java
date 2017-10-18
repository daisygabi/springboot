package com.gra.smarthome.model;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "device")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceId")
    private long deviceId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="active", nullable = false)
    private boolean active;

    private Room room;

    public Device() {}

    public Device(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    @ManyToOne
    @JoinColumn(name = "room_id")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DeviceId: " + deviceId);
        buffer.append(", Name: " + name);
        buffer.append(", Active: " + active);
        return buffer.toString();
    }
}