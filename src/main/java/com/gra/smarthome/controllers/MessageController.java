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
public class MessageController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hello")
    @Transactional(readOnly = true)
    public String printWelcomeMessage() {
        return "How is your day? :)";
    }
}