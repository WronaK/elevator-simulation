package com.example.elevatorsimulation;

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
    private List<Elevator> freeElevators = new ArrayList<>();
    private List<Elevator> elevatorsGoUp = new ArrayList<>();
    private List<Elevator> elevatorsGoDown = new ArrayList<>();

    public void createElevator() {
        Elevator elevator = new Elevator(++countElevator);
        elevatorMap.put(countElevator, elevator);
        freeElevators.add(elevator);
    }

    public List<ElevatorStateDto> getCollectionElevator() {
        return elevatorMap
                .values()
                .parallelStream()
                .map(el -> new ElevatorStateDto(el))
                .collect(Collectors.toList());

    }

    public Elevator findElevatorById(int idElevator) {
        return elevatorMap.get(idElevator);
    }

    public void changeDirectionMove(Elevator elevator, Direction previousDirection, Direction presentDirection) {
        removeElevator(elevator, previousDirection);
        addElevator(elevator, presentDirection);
    }

    private void removeElevator(Elevator elevator, Direction direction) {
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

    private void addElevator(Elevator elevator, Direction direction) {
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
    }
}
