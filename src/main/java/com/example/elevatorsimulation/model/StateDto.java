package com.example.elevatorsimulation.model;

import lombok.Data;

@Data
public class StateDto {
    private int idElevator;
    private int currentFloor;
    private int targetFloor;

    public StateDto(Elevator elevator) {
        this.idElevator = elevator.getIdElevator();
        this.currentFloor = elevator.getCurrentFloor();
        this.targetFloor = elevator.getTargetFloor();
    }

    @Override
    public String toString() {
        return "[" + idElevator +
                ", " + currentFloor +
                ", " + targetFloor +
                ']';
    }
}
