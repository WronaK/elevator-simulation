package com.example.elevatorsimulation;

import java.util.List;

public interface ElevatorSystemInterface {
    void pickup(int fillingFloor, Direction direction);
    void update(int idElevator, int currentFloor, int targetFloor);
    List<ElevatorStateDto> status(int idElevator, int currentFloor, int targetFloor);
    void step();
}
