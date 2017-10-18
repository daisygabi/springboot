package com.gra.smarthome.controllers;


import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    @Test
    public void findDeviceById_ShouldReturnNullPointer() throws Exception {
        when(deviceService.findDeviceById(null)).thenThrow(new NullPointerException(""));
        verifyNoMoreInteractions(deviceService);
    }

    @Test
    public void findDeviceById() throws Exception {
        Device device = new DeviceBuilder()
                .withActiveDevice(false)
                .withName("RandomDevice")
                .build();

        when(deviceService.findDeviceById(any(Long.class))).thenReturn(device);
        verifyNoMoreInteractions(deviceService);
    }

    @Test
    public void create() throws Exception {
        Device device = new DeviceBuilder()
                .withActiveDevice(true)
                .withName("New Alexa")
                .build();

        given(deviceService.create(device)).willReturn(new Device("New Alexa", true));
    }

    @Test
    public void createNewNullNameForDevice_ShouldFail() throws Exception {
        Device device = new DeviceBuilder()
                .withActiveDevice(true)
                .withName(null)
                .build();

        given(deviceService.create(device)).willReturn(null);
    }
}