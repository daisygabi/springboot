package com.gra.smarthome.controllers;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SmartHomeControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8181/ourhome";
    private static final String URL_GET_ALL_DEVICES = "/listdevices";
    private static final String URL_CREATE_DEVICE = "/newDevice";

    private RestTemplate restTemplate = new RestTemplate();

    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private SmartHomeController smartHomeController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(smartHomeController)
                .build();
    }

    @Test
    public void getAllDevices() throws Exception {
        Device activeDevice = new DeviceBuilder()
                .getActiveDevice(true)
                .getName("Alexa")
                .getDeviceId(1L)
                .getHomeId(1L)
                .build();
        Device inactiveDevice = new DeviceBuilder()
                .getActiveDevice(false)
                .getName("Heater")
                .getDeviceId(2L)
                .getHomeId(1L)
                .build();

        when(deviceService.getDevices(1L)).thenReturn(Arrays.asList(activeDevice, inactiveDevice));

        mockMvc.perform(get(BASE_URL + URL_GET_ALL_DEVICES))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(deviceService, times(1)).getDevices(1L);
        verifyNoMoreInteractions(deviceService);
    }

    @Test
    public void createNewDevice() {
        Device deviceBuilder = new DeviceBuilder()
                .getActiveDevice(false)
                .getDeviceId(1L)
                .getHomeId(1L)
                .getName("RandomDevice")
                .build();

        ResponseEntity<Device> responseEntity =
                restTemplate.postForEntity(BASE_URL + URL_CREATE_DEVICE, deviceBuilder, Device.class);
        Device client = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("RandomDevice", client.getName());
    }

    @Test
    public void findDeviceById_ShouldReturnHttpStatusCode404() throws Exception {
        when(deviceService.findDeviceById(1L)).thenThrow(new NullPointerException(""));

        mockMvc.perform(get("/{deviceId}", 1L))
                .andExpect(status().isNotFound());

        verify(deviceService, times(1)).findDeviceById(1L);
        verifyNoMoreInteractions(deviceService);
    }
}