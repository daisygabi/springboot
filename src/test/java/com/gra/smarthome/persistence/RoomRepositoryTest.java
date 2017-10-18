package com.gra.smarthome.persistence;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.model.Room;
import com.gra.smarthome.utils.RoomBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RoomRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoomRepository roomRepository;

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
        // Given
        Room room = new RoomBuilder()
                .withName("Demo")
                .withPopulatedDeviceList(deviceDummySet)
                .build();

        entityManager.persist(room);
        entityManager.flush();

        // When
        Room testRoom = roomRepository.findOne(room.getId());

        // Then
        assertThat(testRoom.getName()).isEqualTo(room.getName());
    }
}