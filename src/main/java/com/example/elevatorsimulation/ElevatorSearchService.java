package com.example.elevatorsimulation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.Math.abs;

@Service
@AllArgsConstructor
public class ElevatorSearchService {
    private ElevatorRepository elevatorRepository;

    public Elevator find(int fillingFloor, Direction direction) {
        Elevator elevator;
        if(direction == Direction.UP) {
            elevator = findGetUpDirectionElevator(fillingFloor);
        } else {
            elevator = findGetDownDirectionElevator(fillingFloor);
        }

        if(Objects.isNull(elevator))
            elevator = findGetFreeElevator(fillingFloor);
            initialFreeElevator(elevator, direction);
        return elevator;
    }

    private Elevator findGetFreeElevator(int fillingFloor) {
        Elevator elevator = null;
        int distance = 100;
        for (Elevator e: elevatorRepository.getFreeElevators()) {
            if(e.getCurrentFloor() == fillingFloor) {
                return e;
            } else {
                if(fillingFloor > e.getCurrentFloor() && distance < abs(fillingFloor - e.getCurrentFloor())) {
                    distance = abs(fillingFloor - e.getCurrentFloor());
                    elevator = e;
                }
            }
        }
        return elevator;
    }

    private Elevator findGetUpDirectionElevator(int fillingFloor) {
        Elevator elevator = null;
        int distance = 100;
        for (Elevator e: elevatorRepository.getElevatorsGoUp()) {
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

    private Elevator findGetDownDirectionElevator(int fillingFloor) {
        Elevator elevator = null;
        int distance = 100;
        for (Elevator e: elevatorRepository.getElevatorsGoDown()) {
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

    private void initialFreeElevator(Elevator elevator, Direction direction) {
        elevatorRepository.getFreeElevators().remove(elevator);
        if(direction == Direction.UP) {
            elevatorRepository.getElevatorsGoUp().add(elevator);
        } else {
            elevatorRepository.getElevatorsGoDown().add(elevator);
        }
    }

}
