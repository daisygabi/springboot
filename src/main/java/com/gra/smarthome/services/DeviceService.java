package com.gra.smarthome.services;

import com.gra.smarthome.model.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService implements DeviceServiceDao {

    public DeviceService(){}

    private List<Device> devices;

    @Override
    public List<Device> getDevices(long homeId) {
        return devices;
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

    @Override
    public Device findDeviceById(long deviceId) {
        return new Device(deviceId, "RandomDevice", false);
    }
}