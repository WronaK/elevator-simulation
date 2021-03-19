package com.example.elevatorsimulation;

import lombok.Data;

@Data
public class ElevatorStateDto {
    public int idElevator;
    private int currentFloor;
    private int targetFloor;
}
