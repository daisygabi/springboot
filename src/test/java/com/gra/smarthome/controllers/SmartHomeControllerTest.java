package com.gra.smarthome.controllers;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SmartHomeControllerTest {

    @Autowired
    private DeviceService mockDeviceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_ShowWelcomeMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/helloVismaTeam").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("How is your day? :)")));
    }

    /**
     * @throws Exception
     */
    @Test
    public void getHomeRegisteredDevices() throws Exception {

        Device activeDevice = new DeviceBuilder()
                .getActiveDevice(true)
                .getName("Alexa")
                .getRandomHomeId(1)
                .build();
        Device inativeDevice = new DeviceBuilder()
                .getInactiveDevice(false)
                .getName("Heater")
                .getRandomHomeId(2)
                .build();

        when(mockDeviceService.getDevices(1)).thenReturn(Arrays.asList(activeDevice, inativeDevice));

        mockMvc.perform(get("/{homeId}/devices"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].deviceId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Alexa")))
                .andExpect(jsonPath("$[0].active", is(true)))
                .andExpect(jsonPath("$[1].deviceId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Heater")))
                .andExpect(jsonPath("$[1].active", is(false)));

        verify(mockDeviceService, times(1)).getDevices(1);
        verifyNoMoreInteractions(mockDeviceService);
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

        when(mockDeviceService.isDeviceActive(1, activeDevice.getDeviceId())).thenReturn(true);

        verify(mockDeviceService, times(2)).isDeviceActive(1, activeDevice.getDeviceId());
        verifyNoMoreInteractions(mockDeviceService);
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
                .build();
        when(mockDeviceService.isDeviceActive(1, inactiveDevice.getDeviceId())).thenReturn(false);

        verify(mockDeviceService, times(2)).isDeviceActive(1, inactiveDevice.getDeviceId());
        verifyNoMoreInteractions(mockDeviceService);
    }

    @Test
    public void findDeviceById_ShouldReturnNullPointer() throws Exception {
        when(mockDeviceService.findDeviceById(1L)).thenThrow(new NullPointerException(""));

        mockMvc.perform(get("/{homeId}/{deviceId}", 1L))
                .andExpect(status().isNotFound());

        verify(mockDeviceService, times(1)).findDeviceById(1L);
        verifyNoMoreInteractions(mockDeviceService);
    }

    @Test
    public void findById() throws Exception {
        Device device = new DeviceBuilder()
                .getActiveDevice(false)
                .getName("RandomDevice")
                .build();

        when(mockDeviceService.findDeviceById(1L)).thenReturn(device);

        mockMvc.perform(get("/{homeId}/{deviceId}", 1L))
                .andExpect(status().isFound());

        verify(mockDeviceService, times(1)).findDeviceById(1L);
        verifyNoMoreInteractions(mockDeviceService);
    }
}