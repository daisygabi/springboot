package com.gra.smarthome.utils;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.model.Room;

import java.util.HashSet;
import java.util.Set;

/**
 * Used in testing to get a Room if data does not matter
 */
public class RoomBuilder {

    private String name = new String();
    private Set<Device> deviceSet;

    public RoomBuilder withPopulatedDeviceList(Set<Device> deviceSet) {
        this.deviceSet = deviceSet;

        return this;
    }

    public RoomBuilder withEmptyDeviceList() {
        this.deviceSet = new HashSet<Device>();

        return this;
    }

    public RoomBuilder withName(String name) {
        this.name = name;

        return this;
    }

    public RoomBuilder withNullName() {
        this.name = null;

        return this;
    }

    public RoomBuilder withNullDeviceLice() {
        this.deviceSet = null;

        return this;
    }

    /**
     * Call this when you want to create a Room object for testing preferences
     *
     * @return
     */
    public Room build() {
        return new Room(name);
    }
}