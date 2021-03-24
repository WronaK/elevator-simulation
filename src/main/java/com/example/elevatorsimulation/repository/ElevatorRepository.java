package com.example.elevatorsimulation.repository;

import com.example.elevatorsimulation.model.Direction;
import com.example.elevatorsimulation.model.Elevator;
import com.example.elevatorsimulation.model.StateDto;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Repository
public class ElevatorRepository {

    @Getter(value = AccessLevel.NONE)
    private Map<Integer, Elevator> elevatorMap = new HashMap<>();

    private int countElevator = 0;
    private List<Elevator> freeElevators;
    private List<Elevator> elevatorsGoUp;
    private List<Elevator> elevatorsGoDown;
    private List<Elevator> elevatorsTransfer;

    public ElevatorRepository() {
        freeElevators = new LinkedList<>();
        elevatorsGoUp = new LinkedList<>();
        elevatorsGoDown = new LinkedList<>();
        elevatorsTransfer = new LinkedList<>();
    }

    public void createElevator() {
        Elevator elevator = new Elevator(++countElevator);
        elevatorMap.put(countElevator, elevator);
        freeElevators.add(elevator);
    }

    public List<StateDto> getElevatorsState() {
        return elevatorMap
                .values()
                .parallelStream()
                .map(StateDto::new)
                .collect(Collectors.toList());
    }

    public Elevator findElevatorById(int idElevator) {
        return elevatorMap.get(idElevator);
    }

    public void changeDirectionMove(Elevator elevator, Direction previousDirection, Direction presentDirection) {
        unregisterElevatorDirection(elevator, previousDirection);
        registerElevatorDirection(elevator, presentDirection);
    }

    private void unregisterElevatorDirection(Elevator elevator, Direction direction) {
        switch (direction) {
            case UP:
                elevatorsGoUp.remove(elevator);
                break;
            case DOWN:
                elevatorsGoDown.remove(elevator);
                break;
            case NONE:
                freeElevators.remove(elevator);
                break;
        }
    }

    public void registerElevatorDirection(Elevator elevator, Direction direction) {
        switch (direction) {
            case UP:
                elevatorsGoUp.add(elevator);
                break;
            case DOWN:
                elevatorsGoDown.add(elevator);
                break;
            case NONE:
                freeElevators.add(elevator);
                break;
        }
        elevator.setDirection(direction);
    }

    public void addToTransfer(Elevator elevator) {
        elevatorsTransfer.add(elevator);
    }

    public void removeFromTransfer(Elevator elevator) {
        elevatorsTransfer.remove(elevator);
    }

    public Elevator checkIsTransfer(int fillingFloor, Direction direction) {
        for(Elevator e: elevatorsTransfer) {
            if(e.getTargetFloor() == fillingFloor && e.getDirection() == direction) {
                return e;
            }
        }
        return null;
    }
}
