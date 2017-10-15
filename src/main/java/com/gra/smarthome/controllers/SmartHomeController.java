package com.gra.smarthome.controllers;

import java.util.List;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.services.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping(value = "/ourhome")
public class SmartHomeController {

    @Autowired
    private DeviceService deviceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hello")
    @Transactional(readOnly = true)
    public String printWelcomeMessage() {
        return "How is your day? :)";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{homeId}/devices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Device> getHomeRegisteredDevices(@PathVariable long homeId) {
        return deviceService.getDevices(homeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{homeId}/{deviceId}")
    @ResponseBody
    public boolean isDeviceActive(@PathVariable long homeId, @PathVariable long deviceId) {
        return false;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/newDevice")
    public Device create(@RequestBody Device device) {
        deviceService.create(device);
        return device;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{deviceId}")
    public void update(@RequestBody Device device,
                       @PathVariable Long id) {
        deviceService.update(device);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{deviceId}")
    public void delete(@PathVariable long id) {
        deviceService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{deviceId}")
    public Device findDeviceById(@PathVariable long id) {
        return deviceService.findDeviceById(id);
    }
}