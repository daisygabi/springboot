package com.gra.smarthome.services;

import com.gra.smarthome.model.Device;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Acts like a contract between the Dao and the Controller
 */
@Repository
public interface DeviceService {

    List<Device> getDevices();

    Device create(Device device);

    void delete(Long deviceId);

    void update(Device device);

    Device findDeviceById(Long deviceId);
}