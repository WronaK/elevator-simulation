package com.example.elevatorsimulation;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.el.ELException;
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
}
