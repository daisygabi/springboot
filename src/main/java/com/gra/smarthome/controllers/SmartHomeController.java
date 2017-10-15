package com.gra.smarthome.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        return deviceService.isDeviceActive(homeId, deviceId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{homeId}/add/device", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addDeviceToHome(@PathVariable long homeId, @PathVariable long deviceId, @PathVariable String name, @PathVariable boolean active) {
        Device device = new Device(deviceId, name, active);
        device.setHomeId(homeId);

        deviceService.create(device);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/")
    public Device create(@RequestBody Device device) {
        deviceService.create(device);
        return device;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value="/{deviceId}")
    public void update(@RequestBody Device device,
                       @PathVariable Long id) {
        deviceService.update(device);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value="/{deviceId}")
    public void delete(@PathVariable long id) {
        deviceService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{deviceId}")
    public Device findDeviceById(@PathVariable long id) {
        return deviceService.findDeviceById(id);
    }
}