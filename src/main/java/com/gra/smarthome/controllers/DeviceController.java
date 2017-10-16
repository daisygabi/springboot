package com.gra.smarthome.controllers;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/devices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Device> getHomeRegisteredDevices() {
        return deviceService.getDevices();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Device> create(@RequestBody Device device) {
        if(device != null) {
            deviceService.create(device);
            return new ResponseEntity<>(device, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void update(@RequestBody Long id,
                       Model model) {
        Device device = deviceService.findDeviceById(id);
        model.addAttribute("device", device);
        deviceService.update(device);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable long id) {
        deviceService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Device> findDeviceById(@PathVariable Long id) {
        Device device = deviceService.findDeviceById(id);
        if(device != null) {
            return new ResponseEntity<>(device, HttpStatus.FOUND);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}