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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DeviceControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8181/device";

    private RestTemplate restTemplate = new RestTemplate();

    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(deviceController)
                .build();
    }

    @Test
    public void getAllDevices() throws Exception {
        Device activeDevice = new DeviceBuilder()
                .withActiveDevice(true)
                .withName("Alexa")
                .withDeviceId(1L)
                .build();
        Device inactiveDevice = new DeviceBuilder()
                .withActiveDevice(false)
                .withName("Heater")
                .withDeviceId(2L)
                .build();

        when(deviceService.getDevices()).thenReturn(Arrays.asList(activeDevice, inactiveDevice));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewDevice() {
        Device deviceBuilder = new DeviceBuilder()
                .withActiveDevice(false)
                .withDeviceId(1L)
                .withName("RandomDevice")
                .build();

        ResponseEntity<Device> responseEntity =
                restTemplate.postForEntity(BASE_URL , deviceBuilder, Device.class);
        Device client = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("RandomDevice", client.getName());
    }

    @Test(expected = HttpClientErrorException.class)
    public void findEmptyDeviceById_ShouldReturnHttpStatusCode404() throws Exception {
        when(deviceService.findDeviceById(0L)).thenThrow(new NullPointerException(""));

        ResponseEntity<Device> responseEntity =
                restTemplate.getForEntity(BASE_URL + "/0", Device.class);

//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void findDeviceById() throws Exception {
        when(deviceService.findDeviceById(1L)).thenReturn(new Device(1L, "RandonDevice", true));

        ResponseEntity<Device> responseEntity =
                restTemplate.getForEntity(BASE_URL + "/1", Device.class);
        Device client = responseEntity.getBody();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals("RandomDevice", client.getName());
    }
}