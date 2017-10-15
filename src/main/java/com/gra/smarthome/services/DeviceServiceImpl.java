package com.gra.smarthome.services;

import com.gra.smarthome.model.Device;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Acts like a contract between the Dao and the Controller
 */
@Repository
public interface DeviceServiceImpl {

    public List<Device> getDevices(long homeId);
    public boolean isDeviceActive(long homeId, long deviceId);
    public void create(Device device);
    public void delete(long deviceId);
    public void update(Device device);

    public Device findDeviceById(long deviceId);
}