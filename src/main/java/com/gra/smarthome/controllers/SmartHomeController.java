package com.gra.smarthome.controllers;

import java.util.List;

import com.gra.smarthome.model.Device;
import com.gra.smarthome.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
public class SmartHomeController {

	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "/helloVismaTeam", method = RequestMethod.GET)
	public String printWelcomeVismaMessage(ModelMap model) {
		model.addAttribute("message", "Hello Visma team :)");
		return "How is your day? :)";
	}

	@GetMapping("/{homeId}/devices")
	public List<Device> getHomeRegisteredDevices(@PathVariable long homeId) {
		return deviceService.getDevices(homeId);
	}

	@GetMapping("/{homeId}/{deviceId}")
	public boolean isDeviceActive(@PathVariable long homeId, @PathVariable long deviceId) {
		return deviceService.isDeviceActive(homeId, deviceId);
	}

//	@PostMapping("/students/{studentId}/courses")
//	public ResponseEntity<Void> addDevice(
//			@PathVariable String studentId, @RequestBody Course newCourse) {
//
//		Course course = studentService.addCourse(studentId, newCourse);
//
//		if (course == null)
//			return ResponseEntity.noContent().build();
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
//				"/{id}").buildAndExpand(course.getId()).toUri();
//
//		return ResponseEntity.created(location).build();
//	}
}