package com.gra.smarthome.controllers;


import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8181/ourhome";

    @Test
    public void showWelcomeMessage() throws Exception {
        String message = restTemplate.getForObject(BASE_URL + "/hello", String.class);

        Assert.assertEquals("How is your day? :)", message);
        assertNotNull(message);
    }
}