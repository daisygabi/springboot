package com.gra.smarthome.utils;

import com.gra.smarthome.model.Device;

/**
 * Used in testing to get a Device if data does not matter
 */
public class DeviceBuilder {

    private long deviceId = (long) Math.random();
    private String name = new String();
    private boolean active = false;

    public DeviceBuilder withActiveDevice(boolean active) {
        this.active = active;

        return this;
    }

    public DeviceBuilder withInactiveDevice(boolean active) {
        this.active = active;

        return this;
    }

    public DeviceBuilder withName(String name) {
        this.name = name;

        return this;
    }

    public DeviceBuilder withNullName() {
        this.name = null;

        return this;
    }

    public DeviceBuilder withNameTooLong(String name) {
        this.name = name;

        return this;
    }


    /**
     * Call this when you want to create a Device Object for testing preferences
     *
     * @return
     */
    public Device build() {
        return new Device(name, active);
    }
}