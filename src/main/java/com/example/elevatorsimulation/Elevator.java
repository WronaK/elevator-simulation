package com.example.elevatorsimulation;

import java.util.*;

public class Elevator {
    private int idElevator;
    private int currentFloor;
    private int targetFloor;
    private int numberFloors;
    private StateElevator stateElevator;
    private Map<Integer, Integer> updateElevator;
    private Set<Integer> elevatorNotifications;

    public Elevator(int numberFloors) {
        this.numberFloors = numberFloors;
        this.stateElevator = StateElevator.FREE;
        this.elevatorNotifications = new HashSet<>();
        this.updateElevator = new HashMap<>();
    }

    public int getIdElevator() {
        return idElevator;
    }

    public void updateElevator(int currentFloor, int targetFloor) {
        updateElevator.put(currentFloor, targetFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void addFloor(int floor) {
        elevatorNotifications.add(floor);
    }
}
