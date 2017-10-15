package com.gra.smarthome.services;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.persistence.DeviceRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Device> getDevices() {
        return Lists.newArrayList(deviceRepository.findAll());
    }

    @Override
    public Device create(Device device) {
        deviceRepository.save(device);
        return device;
    }

    @Override
    public void delete(Long deviceId) {
        deviceRepository.delete(deviceId);
    }

    @Override
    public void update(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public Device findDeviceById(Long deviceId) {
        return deviceRepository.findDeviceByDeviceId(deviceId);
    }
}