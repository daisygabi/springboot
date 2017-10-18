package com.gra.smarthome.controllers;


import com.gra.smarthome.model.Device;
import com.gra.smarthome.model.Room;
import com.gra.smarthome.persistence.RoomRepository;
import com.gra.smarthome.services.DeviceService;
import com.gra.smarthome.utils.DeviceBuilder;
import com.gra.smarthome.utils.RoomBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {

    @Mock
    private RoomRepository repository;

    private Set<Device> deviceDummySet;

    @Before
    public void setUp() {
        deviceDummySet = new HashSet<Device>(){{
            add(new Device("Hallway 1", true));
            add(new Device("Hallway 2", false));
            add(new Device("DHallway 3", true));
        }};
    }

    @Test
    public void findRoomById() throws Exception {
        Room room = new RoomBuilder()
                .withName("Hallway")
                .withPopulatedDeviceList(deviceDummySet)
                .build();

        when(repository.findOne(any(Long.class))).thenReturn(room);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void createNewDevice() throws Exception {
        Room room = new RoomBuilder()
                .withName("Hallway New")
                .withPopulatedDeviceList(deviceDummySet)
                .build();

        given(repository.save(room)).willReturn(new Room("New Alexa", deviceDummySet));
    }

    @Test
    public void createNewRoom_WithNullDevices() throws Exception {
        Room room = new RoomBuilder()
                .withName("No Device Room")
                .withNullDeviceLice()
                .build();

        given(repository.save(room)).willReturn(new Room("No Device Room", null));
    }
}