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
    private TreeSet<Integer> floorsStopsDirectionUp;
    private TreeSet<Integer> floorsStopsDirectionDown;

    public Elevator(int idElevator) {
        this.idElevator = idElevator;
        this.currentFloor = 0;
        this.targetFloor = 0;
        this.direction = Direction.NONE;
        floorsStopsDirectionDown = new TreeSet<>();
        floorsStopsDirectionUp = new TreeSet<>();
    }

    public void addAnotherFloor(int floor) {
        if (direction == Direction.UP) {
            addToUp(floor);
        } else {
            addToDown(floor);
        }
    }

    private void addToUp(int floor) {
        if(floor < currentFloor) {
            floorsStopsDirectionDown.add(floor);
        } else {
            floorsStopsDirectionUp.add(floor);
        }
    }

    private void addToDown(int floor) {
        if(floor > currentFloor) {
            floorsStopsDirectionUp.add(floor);
        } else {
            floorsStopsDirectionDown.add(floor);
        }
    }

    public void moveDown() {
        currentFloor--;
        if(!floorsStopsDirectionDown.isEmpty()) {
            targetFloor = floorsStopsDirectionDown.last();
            if(targetFloor == currentFloor)
                floorsStopsDirectionDown.remove(targetFloor);
        } else if(!floorsStopsDirectionUp.isEmpty()) {
            direction = Direction.UP;
            targetFloor = floorsStopsDirectionUp.first();
            if(targetFloor == currentFloor)
                floorsStopsDirectionUp.remove(targetFloor);
        } else {
            direction = Direction.NONE;
        }
    }

    public void moveUp() {
        currentFloor++;
        if(!floorsStopsDirectionUp.isEmpty()) {
            targetFloor = floorsStopsDirectionUp.first();
            if(targetFloor == currentFloor)
                floorsStopsDirectionUp.remove(targetFloor);
        } else if(!floorsStopsDirectionDown.isEmpty()) {
            direction = Direction.DOWN;
            targetFloor = floorsStopsDirectionDown.last();
            if(targetFloor == currentFloor)
                floorsStopsDirectionDown.remove(targetFloor);
        } else {
            direction = Direction.NONE;
        }
    }
}
