package com.gra.smarthome.utils;

import com.gra.smarthome.model.Device;

/**
 * Used in testing to get a Device if data does not matter
 */
public class DeviceBuilder {

    private long deviceId = (long) Math.random();
    private String name = new String();
    private boolean active = false;
    private long homeId;

    public DeviceBuilder getHomeId(long homeId) {
        this.homeId = homeId;
        return this;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public DeviceBuilder getDeviceId(long deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public DeviceBuilder getActiveDevice(boolean active) {
        this.active = active;

        return this;
    }

    public DeviceBuilder getInactiveDevice(boolean active) {
        this.active = active;

        return this;
    }

    public DeviceBuilder getName(String name) {
        this.name = name;

        return this;
    }

    public DeviceBuilder getNullName() {
        this.name = null;

        return this;
    }

    public DeviceBuilder getNameTooLong(String name) {
        this.name = name;

        return this;
    }


    /**
     * Call this when you want to create a Device Object for testing preferences
     *
     * @return
     */
    public Device build() {
        return new Device(getDeviceId(), name, active);
    }
}