package com.gra.smarthome.controllers;

import com.gra.smarthome.model.Room;
import com.gra.smarthome.persistence.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository repository;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Room>> getRoomsWithActiveDevices() {
        //TODO add predicate or filter to get only rooms that have active devices attached
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
}