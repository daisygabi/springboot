package com.gra.smarthome.services;

import com.gra.smarthome.model.Device;

import java.util.List;

/**
 *
 */
public interface DeviceServiceDao {

    public List<Device> getDevices(long homeId);
    public boolean isDeviceActive(long homeId, long deviceId);
    public void addDevice(Device device, long homeId);
    public void deleteDevice(long deviceId, long homeId);
    public void updateDevice(Device device, long homeId);

    public Device findDeviceById(long deviceId);
}
