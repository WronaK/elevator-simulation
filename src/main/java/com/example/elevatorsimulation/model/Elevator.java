package com.example.elevatorsimulation.model;

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

    public void addAnotherFloor(int currentFloor, int targetFloor) {
        if (targetFloor > currentFloor) {
            floorsStopsDirectionUp.add(targetFloor);
        } else {
            floorsStopsDirectionDown.add(targetFloor);
        }
    }

    public void moveDown() {
        if (!floorsStopsDirectionDown.isEmpty()) {
            targetFloor = floorsStopsDirectionDown.last();
            if (targetFloor == currentFloor) {
                floorsStopsDirectionDown.remove(targetFloor);
            } else {
                currentFloor--;
            }
        } else if (!floorsStopsDirectionUp.isEmpty()) {
            direction = Direction.UP;
            targetFloor = floorsStopsDirectionUp.first();
            if (targetFloor == currentFloor) {
                floorsStopsDirectionUp.remove(targetFloor);
            } else {
                currentFloor++;
            }
        } else {
            direction = Direction.NONE;
        }
    }

    public void moveUp() {
        if (!floorsStopsDirectionUp.isEmpty()) {
            targetFloor = floorsStopsDirectionUp.first();
            if (targetFloor == currentFloor) {
                floorsStopsDirectionUp.remove(targetFloor);
            } else {
                currentFloor++;
            }
        } else if (!floorsStopsDirectionDown.isEmpty()) {
            direction = Direction.DOWN;
            targetFloor = floorsStopsDirectionDown.last();
            if (targetFloor == currentFloor) {
                floorsStopsDirectionDown.remove(targetFloor);
            } else  {
                currentFloor--;
            }
        } else {
            direction = Direction.NONE;
        }
    }

    public void transfer() {
        if (targetFloor > currentFloor) {
            currentFloor++;
        } else if (targetFloor < currentFloor) {
            currentFloor--;
        }
    }

    public void removeTransferFloor() {
        this.floorsStopsDirectionDown.remove(targetFloor);
        this.floorsStopsDirectionUp.remove(targetFloor);
    }
}
