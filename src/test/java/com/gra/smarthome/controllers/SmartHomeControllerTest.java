package com.gra.smarthome.controllers;

import com.gra.smarthome.SpringBootFunApplication;
import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

/**
 *
 */
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
    public void getHomeRegisteredDevices() throws Exception {

        Device activeDevice = new DeviceBuilder()
                .getActiveDevice(true)
                .getName("Alexa")
                .getDeviceId(1)
                .getHomeId(1)
                .build();
        Device inativeDevice = new DeviceBuilder()
                .getInactiveDevice(false)
                .getName("Heater")
                .getDeviceId(2)
                .getHomeId(1)
                .build();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(BASE_URL + "/1/devices");

        given(this.deviceService.getDevices(1L)).willReturn(null);

        List response = restTemplate.getForObject(builder.toUriString(), List.class);

        verify(deviceService, times(1)).getDevices(1);
        verifyNoMoreInteractions(deviceService);
    }

    /**
     * Test created device appears to be active when it's pinged
     *
     * @throws Exception
     */
    @Test
    public void isDeviceActive() throws Exception {
        Device activeDevice = new DeviceBuilder()
                .getActiveDevice(true)
                .getName("Alexa")
                .build();

        when(deviceService.isDeviceActive(1L, activeDevice.getDeviceId())).thenReturn(true);

        verifyNoMoreInteractions(deviceService);
    }

    /**
     * Test created device appears to be inactive when it's pinged
     *
     * @throws Exception
     */
    @Test
    public void isDeviceInactive() throws Exception {
        Device inactiveDevice = new DeviceBuilder()
                .getActiveDevice(false)
                .getName("Heater")
                .getHomeId(1)
                .build();
        when(deviceService.isDeviceActive(1, inactiveDevice.getDeviceId())).thenReturn(false);

        verifyNoMoreInteractions(deviceService);
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
}