package com.example.elevatorsimulation.controller;

import com.example.elevatorsimulation.model.StateDto;

import java.util.List;

public interface ElevatorSystemInterface {
    void createElevator(int numberFloors);
    void pickup(int fillingFloor, int direction);
    void update(int idElevator, int currentFloor, int targetFloor);
    List<StateDto> status();
    void step();
}
