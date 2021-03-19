package com.example.elevatorsimulation;

import lombok.Getter;

@Getter
public class Elevator {
    private int idElevator;
    private int currentFloor;
    private int targetFloor;

    public Elevator(int idElevator) {
        this.idElevator = idElevator;
    }
}
