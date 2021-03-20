package com.example.elevatorsimulation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ElevatorSupervisor implements ElevatorSystemInterface {
    private ElevatorSearchService elevatorSearchService;
    private ElevatorRepository elevatorRepository;

    @Override
    public void pickup(int fillingFloor, Direction direction) {
        Elevator elevator = elevatorSearchService.find(fillingFloor, direction);
        elevator.addAnotherFloor(fillingFloor);
    }

    @Override
    public void update(int idElevator, int currentFloor, int targetFloor) {
        Elevator elevator = elevatorRepository.findElevatorById(idElevator);
        elevator.addAnotherFloor(targetFloor);
    }

    @Override
    public void step() {
        //TODO
    }

    @Override
    public List<ElevatorStateDto> status(int idElevator, int currentFloor, int targetFloor) {
        return elevatorRepository.getCollectionElevator();
    }
}
