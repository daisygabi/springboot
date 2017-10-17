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
import sun.invoke.empty.Empty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class DeviceControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8181/device";

    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        // Create dummy Devices
        createDevice("Alexa");
        createDevice("Heater");
        createDevice("Google");
    }

    @Test
    public void getAllDevices() throws Exception {
        restTemplate.getForEntity(BASE_URL, List.class);
    }

    @Test
    public void createNewDevice() {
        ResponseEntity<Device> responseEntity = createDevice("Random Device");
        Device client = responseEntity.getBody();
//
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

    @Test
    public void findDeviceByName() throws Exception {
        ResponseEntity<Device> responseEntity =
                restTemplate.getForEntity(BASE_URL + "/2", Device.class);
        Device client = responseEntity.getBody();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals("Heater", client.getName());
    }

    @Test
    public void deleteDevice() {
        ResponseEntity<Device> responseEntity = createDevice( "My New Device");
        Device device = responseEntity.getBody();

        Map<String, String> map = new HashMap<>();
        map.put("deviceId", device.getDeviceId() + "");

        restTemplate.delete(BASE_URL + "/" + device.getDeviceId(), Device.class);
    }

    private ResponseEntity<Device> createDevice(String name) {
        Device deviceBuilder = new DeviceBuilder()
                .withActiveDevice(false)
                .withName(name)
                .build();

        return restTemplate.postForEntity(BASE_URL, deviceBuilder, Device.class);
    }

    private Device extractDevice(ResponseEntity<Device[]> responseEntity) {
        return responseEntity.getBody()[0];
    }
}