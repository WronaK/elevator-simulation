package com.example.elevatorsimulation.controller;

import com.example.elevatorsimulation.model.StateDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ElevatorController {
    private ElevatorSystemInterface elevatorSystemInterface;

    @GetMapping("/status")
    public List<StateDto> status() {
        return elevatorSystemInterface.status();
    }

    @PostMapping("/create")
    public void createElevator(@RequestParam int numberFloors) {
        elevatorSystemInterface.createElevator(numberFloors);
    }

    @PostMapping("/step")
    public void step() {
        elevatorSystemInterface.step();
    }

    @PutMapping("/update")
    public void update(@RequestParam int id, @RequestParam int currentFloor, @RequestParam int targetFloor) {
        elevatorSystemInterface.update(id, currentFloor, targetFloor);
    }

    @PutMapping("/pickup")
    public void pickup(@RequestParam int fillingFloor, @RequestParam int direction) {
        elevatorSystemInterface.pickup(fillingFloor, direction);
    }

}
