package com.gra.smarthome.persistence;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.utils.DeviceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DeviceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void findDeviceById() throws Exception {
        // Given
        Device device = new DeviceBuilder()
                .withName("Demo")
                .withActiveDevice(true)
                .build();


        entityManager.persist(device);
        entityManager.flush();

        // When
        Device testDevice = deviceRepository.findDeviceByDeviceId(device.getDeviceId());

        // Then
        assertThat(testDevice.getName()).isEqualTo(device.getName());
    }
}