package com.gra.smarthome.controllers;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.utils.DeviceBuilder;
import org.hibernate.service.spi.InjectService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DeviceControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8181/device";

    private RestTemplate restTemplate = new RestTemplate();

    private MockMvc mockMvc;

    @InjectMocks
    private DeviceController deviceController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(deviceController)
                .build();

        // Create dummy Devices
        createDevice(1L);
        createDevice(2L);
    }

    @Test
    public void getAllDevices() throws Exception {
        restTemplate.getForEntity(BASE_URL + "/devices", List.class);
    }

    @Test
    public void createNewDevice() {
        ResponseEntity<Device> responseEntity = createDevice(1L);
        Device client = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("RandomDevice", client.getName());
    }

    @Test
    public void findEmptyDeviceById_ShouldReturnHttpStatusCode404() throws Exception {
        try {
            restTemplate.getForEntity(BASE_URL + "/0", Device.class);
        } catch (HttpClientErrorException ex) {
            assertEquals(ex.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void findDeviceById() throws Exception {
        ResponseEntity<Device> responseEntity =
                restTemplate.getForEntity(BASE_URL + "/2", Device.class);
        Device client = responseEntity.getBody();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals("RandomDevice", client.getName());
    }

    private ResponseEntity<Device> createDevice(Long id) {
        Device deviceBuilder = new DeviceBuilder()
                .withActiveDevice(false)
                .withDeviceId(id)
                .withName("RandomDevice")
                .build();

        return restTemplate.postForEntity(BASE_URL, deviceBuilder, Device.class);
    }
}