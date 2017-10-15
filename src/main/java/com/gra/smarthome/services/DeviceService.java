package com.gra.smarthome.services;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.persistence.DeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeviceService implements DeviceServiceImpl {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public void create(Device device) {
        deviceDao.create(device);
    }

    @Override
    public List<Device> getDevices(long homeId) {
        return deviceDao.getDevices(homeId);
    }

    @Override
    public boolean isDeviceActive(long homeId, long deviceId) {
        return deviceDao.isDeviceActive(homeId, deviceId);
    }


    @Override
    public void delete(long deviceId) {
        deviceDao.delete(deviceId);
    }

    @Override
    public void update(Device device) {
        deviceDao.update(device);
    }

    @Override
    public Device findDeviceById(long deviceId) {
        return deviceDao.findDeviceById(deviceId);
    }
}