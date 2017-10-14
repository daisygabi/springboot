package com.gra.smarthome.controllers;

import java.util.List;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/{homeId}/devices")
    public List<Device> getHomeRegisteredDevices(@PathVariable long homeId) {
        return deviceService.getDevices(homeId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{homeId}/{deviceId}")
    public boolean isDeviceActive(@PathVariable long homeId, @PathVariable long deviceId) {
        return deviceService.isDeviceActive(homeId, deviceId);
    }
}