package com.gra.smarthome.controllers;


import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceServiceImpl;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SmartHomeControllerTest {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8181/ourhome";
    private static final String URL_GET_ALL_DEVICES = "/listdevices";
    private static final String URL_CREATE_DEVICE = "/newDevice";
    private static final String URL_UPDATE_DEVICE = "/device/{id}";
    private static final String URL_DELETE_DEVICE = "/device/{id}";


    @Mock
    private DeviceServiceImpl deviceServiceImpl;

    @Test
    public void showWelcomeMessage() throws Exception {
        String message = restTemplate.getForObject(BASE_URL + "/hello", String.class);

        Assert.assertEquals("How is your day? :)", message);
        assertNotNull(message);
    }

    @Test
    public void getHomeRegisteredDevices() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL + "/1/devices");

        given(this.deviceServiceImpl.getDevices(1L)).willReturn(null);
        restTemplate.getForObject(builder.toUriString(), List.class);
    }

//    @Test
//    public void isDeviceActive() throws Exception {
//        Device activeDevice = new DeviceBuilder()
//                .getActiveDevice(true)
//                .getName("Alexa")
//                .build();
//
//        when(deviceServiceImpl.isDeviceActive(1L, activeDevice.getDeviceId())).thenReturn(true);
//
//        verifyNoMoreInteractions(deviceServiceImpl);
//    }

//    @Test
//    public void isDeviceInactive() throws Exception {
//        Device inactiveDevice = new DeviceBuilder()
//                .getActiveDevice(false)
//                .getName("Heater")
//                .getHomeId(1)
//                .build();
//        when(deviceServiceImpl.isDeviceActive(1L, inactiveDevice.getDeviceId())).thenReturn(false);
//
//        verifyNoMoreInteractions(deviceServiceImpl);
//    }

    @Test
    public void findDeviceById_ShouldReturnNullPointer() throws Exception {
        when(deviceServiceImpl.findDeviceById(1L)).thenThrow(new NullPointerException(""));

        verifyNoMoreInteractions(deviceServiceImpl);
    }

    @Test
    public void findHomeDeviceById() throws Exception {
        Device device = new DeviceBuilder()
                .getActiveDevice(false)
                .getName("RandomDevice")
                .build();

        when(deviceServiceImpl.findDeviceById(any(Long.class))).thenReturn(device);
        verifyNoMoreInteractions(deviceServiceImpl);
    }

    @Test
    public void createNewDevice() {
        Device device = new DeviceBuilder()
                .getActiveDevice(true)
                .getName("New Alexa")
                .build();

        Device createdDevice = restTemplate.postForObject(BASE_URL + URL_CREATE_DEVICE, device, Device.class);
        assertNotNull(createdDevice);
    }

    @Test
    public void deletedDevice() {
        restTemplate.delete(BASE_URL + URL_DELETE_DEVICE, 3);
        Device[] devices = restTemplate.getForObject(BASE_URL + URL_GET_ALL_DEVICES, Device[].class);
        Boolean found = false;
        for (Device dev : devices) {
            if (dev.getDeviceId() == 3) {
                found = true;
            }
        }
        assertFalse(found);
        listDevices(devices);
    }

    private void listDevices(Device[] devices) {
        Arrays.stream(devices).forEach(s -> System.out.println(s.toString()));
    }
}