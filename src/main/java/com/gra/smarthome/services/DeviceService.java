package com.gra.smarthome.services;

import com.gra.smarthome.model.Device;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public class DeviceService implements DeviceServiceDao {

    private List<Device> devices;

    public List<Device> getDevices(long homeId) {
        return devices;
    }

    @Override
    public List<Device> getHomeRegisteredDevices(long homeId) {
        return null;
    }

    @Override
    public boolean isDeviceActive(long homeId, long deviceId) {
        return false;
    }

    @Override
    public void addDevice(Device device, long homeId) {

    }

    @Override
    public void deleteDevice(long deviceId, long homeId) {

    }

    @Override
    public void updateDevice(Device device, long homeId) {

    }
}