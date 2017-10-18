package com.gra.smarthome.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    @Size(max = 50, message = "Maximum size for a room's name is 50")
    @NotEmpty(message = "You have to give the Room a name")
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @Column(name = "devices")
    private Set<Device> devices;

    public Room() {}

    public Room(String name) {
        this.name = name;
    }

    public Room(String name, Set<Device> devices) {
        this.name = name;
        this.devices = devices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Room[id=%d, name='%s']%n",
                id, name);
        if (devices != null) {
            for(Device device : devices) {
                result += String.format(
                        "Device[id=%d, name='%s']%n",
                        device.getDeviceId(), device.getName());
            }
        }

        return result;
    }
}