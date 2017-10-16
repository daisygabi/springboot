package com.gra.smarthome.controllers;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.utils.DeviceBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class DeviceControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8181/device";

    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        // Create dummy Devices
        createDevice(1L, "Alexa");
        createDevice(2L, "Heater");
    }

    @Test
    public void getAllDevices() throws Exception {
        restTemplate.getForEntity(BASE_URL + "/devices", List.class);
    }

    @Test
    public void createNewDevice() {
        ResponseEntity<Device> responseEntity = createDevice(3L, "Random Device");
        Device client = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Random Device", client.getName());
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
        assertEquals("Heater", client.getName());
    }

    private ResponseEntity<Device> createDevice(Long id, String name) {
        Device deviceBuilder = new DeviceBuilder()
                .withActiveDevice(false)
                .withDeviceId(id)
                .withName(name)
                .build();

        return restTemplate.postForEntity(BASE_URL, deviceBuilder, Device.class);
    }
}