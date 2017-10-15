package com.gra.smarthome.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "smarthome")
public class SmartHome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homeId")
    private long id;

    @Column(name="location")
    private String location;

    @Column
    @OneToMany
    private List<Device> devices;

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
}