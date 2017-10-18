package com.gra.smarthome.persistence;

import com.gra.smarthome.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {

    Device findDeviceByDeviceId(Long id);
}