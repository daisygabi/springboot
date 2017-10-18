package com.gra.smarthome.persistence;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.model.SmartHome;
import org.springframework.data.repository.CrudRepository;

public interface SmartHomeRepository extends CrudRepository<SmartHome, Long> {
}