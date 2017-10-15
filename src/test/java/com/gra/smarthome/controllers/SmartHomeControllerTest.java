package com.gra.smarthome.controllers;


import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.services.DeviceServiceImpl;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SmartHomeControllerTest {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8181/ourhome";

    @Mock
    private DeviceService deviceService;

    @Test
    public void showWelcomeMessage() throws Exception {
        String message = restTemplate.getForObject(BASE_URL + "/hello", String.class);

        Assert.assertEquals("How is your day? :)", message);
        assertNotNull(message);
    }

    @Test
    public void findDeviceById_ShouldReturnNullPointer() throws Exception {
        when(deviceService.findDeviceById(1L)).thenThrow(new NullPointerException(""));
        verifyNoMoreInteractions(deviceService);
    }

    @Test
    public void findHomeDeviceById() throws Exception {
        Device device = new DeviceBuilder()
                .getActiveDevice(false)
                .getName("RandomDevice")
                .build();

        when(deviceService.findDeviceById(any(Long.class))).thenReturn(device);
        verifyNoMoreInteractions(deviceService);
    }

    @Test
    public void createNewDevice() throws Exception {
        Device device = new DeviceBuilder()
                .getActiveDevice(true)
                .getName("New Alexa")
                .build();

        given(deviceService.create(device)).willReturn(new Device(1L, "New Alexa", true));
    }
}