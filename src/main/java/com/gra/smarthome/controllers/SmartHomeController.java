package com.gra.smarthome.controllers;

import java.util.List;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.services.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
    @RequestMapping(value = "/devices", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(method = RequestMethod.POST, value = "/newDevice")
    public ResponseEntity<Device> create(@RequestBody Device device) {
        deviceService.create(device);
        return new ResponseEntity<Device>(device, HttpStatus.CREATED);
    }

    @GetMapping(value = "/edit/{deviceId}")
    public void update(@RequestBody Long id,
                       Model model) {
        Device device = deviceService.findDeviceById(id);
        model.addAttribute("singer", device);
        deviceService.update(device);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{deviceId}")
    public void delete(@PathVariable long id) {
        deviceService.delete(id);
    }

    @RequestMapping(value = "/{deviceId}", method = RequestMethod.GET)
    public ResponseEntity<Device> findDeviceById(@PathVariable long id) {
        Device device = deviceService.findDeviceById(id);
        return new ResponseEntity<Device>(device, HttpStatus.OK);
    }
}