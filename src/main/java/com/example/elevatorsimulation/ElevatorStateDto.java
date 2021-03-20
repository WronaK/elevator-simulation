package com.example.elevatorsimulation;

import lombok.Data;

@Data
public class ElevatorStateDto {
    private int idElevator;
    private int currentFloor;
    private int targetFloor;

    public ElevatorStateDto(Elevator elevator) {
        this.idElevator = elevator.getIdElevator();
        this.currentFloor = elevator.getCurrentFloor();
        this.targetFloor = elevator.getTargetFloor();
    }
}
