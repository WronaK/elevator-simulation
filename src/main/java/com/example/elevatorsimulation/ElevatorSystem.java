package com.example.elevatorsimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ElevatorSystem implements ElevatorSystemInterface {
    private List<Elevator> freeElevators = new ArrayList<>();
    private List<Elevator> elevatorsGoUp = new ArrayList<>();
    private List<Elevator> elevatorsGoDown = new ArrayList<>();

    public void addNewElevator(Elevator elevator) {
        this.freeElevators.add(elevator);
    }

    public void removeElevator(Elevator elevator) {
        this.freeElevators.remove(elevator);
    }

    @Override
    public void update(int idElevator, int currentFloor, int targetFloor) {
        Elevator elevator = findElevator(idElevator);
        if(elevator != null) {
            elevator.updateElevator(currentFloor, targetFloor);
        }
    }

    @Override
    public void step() {
        //TODO
    }

    @Override
    public List<ElevatorStateDto> status(int idElevator, int currentFloor, int targetFloor) {
        // TODO
        return null;
    }

    @Override
    public void pickup(int fillingFloor, int direction) {
        Elevator elevator = null;
        if(direction < 0) {
            if(!checkLisIsEmpty(elevatorsGoDown)) {
                elevator = getNearestElevatorGoDown(fillingFloor);
            }
        } else if(direction > 0) {
            if(!checkLisIsEmpty(elevatorsGoUp)) {
                elevator = getNearestElevatorGoUp(fillingFloor);
            }
        }
        if(Objects.isNull(elevator)) {
            elevator = freeElevators.get(0);
        }

        elevator.addFloor(fillingFloor);
    }

    private Elevator findElevator(int idElevator) {
        for(Elevator e: freeElevators) {
            if(e.getIdElevator() == idElevator)
                return e;
        }

        for(Elevator e: elevatorsGoUp) {
            if(e.getIdElevator() == idElevator)
                return e;
        }

        for (Elevator e: elevatorsGoDown) {
            if(e.getIdElevator() == idElevator)
                return e;
        }
        return null;
    }

    private boolean checkLisIsEmpty(List<Elevator> list) {
        return list.size() == 0 ? true : false;
    }

    private Elevator getNearestElevatorGoDown(int fillingFloor) {
        Elevator elevator = null;
        int distance = 100;
        for (Elevator e:elevatorsGoDown) {
            if(e.getCurrentFloor() == fillingFloor) {
                return e;
            } else {
                if(fillingFloor < e.getCurrentFloor() && distance < e.getCurrentFloor() - fillingFloor) {
                    distance = e.getCurrentFloor() - fillingFloor;
                    elevator = e;
                }
            }
        }
        return elevator;
    }

    private Elevator getNearestElevatorGoUp(int fillingFloor) {
        Elevator elevator = null;
        int distance = 100;
        for (Elevator e:elevatorsGoUp) {
            if(e.getCurrentFloor() == fillingFloor) {
                return e;
            } else {
                if(fillingFloor > e.getCurrentFloor() && distance < fillingFloor - e.getCurrentFloor()) {
                    distance = fillingFloor - e.getCurrentFloor();
                    elevator = e;
                }
            }
        }
        return elevator;
    }
}
