package com.gra.smarthome;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.model.Room;
import com.gra.smarthome.persistence.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication(scanBasePackages = {"com.gra.smarthome.controllers", "com.gra.smarthome.services",
		"com.gra.smarthome.persistence"})
public class SpringBootFunApplication implements CommandLineRunner {

	@Autowired
	private RoomRepository roomRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFunApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {

		Room livingRoom = new Room("Living Room");
		Set<Device> deviceLivingRoomSet = new HashSet<Device>(){{
			add(new Device("Device Living Room 1", true));
			add(new Device("Device Living Room 2", false));
			add(new Device("Device Living Room 3", true));
		}};
		livingRoom.setDevices(deviceLivingRoomSet);

		Room bedroom = new Room("Bedroom");
		Set<Device> deviceBedroomSet = new HashSet<Device>(){{
			add(new Device("Device Bedroom 1", false));
			add(new Device("Device Bedroom 2", false));
			add(new Device("Device Bedroom 3", true));
		}};
		bedroom.setDevices(deviceBedroomSet);

		roomRepository.save(new HashSet<Room>() {{
			add(livingRoom);
			add(bedroom);
		}});

		for (Room room : roomRepository.findAll()) {
			System.out.println("Room: " + room.toString());
		}
	}
}