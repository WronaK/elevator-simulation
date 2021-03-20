package com.example.elevatorsimulation;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Elevator {
    private int idElevator;
    private int currentFloor;
    private int targetFloor;
    private Direction direction;
    private Set<Integer> openDoorsDirectionUp;
    private Set<Integer> openDoorsDirectionDown;

    public Elevator(int idElevator) {
        this.idElevator = idElevator;
        openDoorsDirectionDown = new HashSet<>();
        openDoorsDirectionUp = new HashSet<>();
    }

    public void addAnotherFloor(int floor) {
        if(direction == Direction.UP) {
            addToUp(floor);
        } else {
            addToDown(floor);
        }

    }

    private void addToUp(int floor) {
        if(floor < currentFloor) {
            openDoorsDirectionDown.add(floor);
        } else {
            openDoorsDirectionUp.add(floor);
        }
    }

    private void addToDown(int floor) {
        if(floor > currentFloor) {
            openDoorsDirectionUp.add(floor);
        } else {
            openDoorsDirectionDown.add(floor);
        }
    }

}
