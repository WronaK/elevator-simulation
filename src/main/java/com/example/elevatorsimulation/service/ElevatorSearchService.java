package com.example.elevatorsimulation.service;

import com.example.elevatorsimulation.model.Direction;
import com.example.elevatorsimulation.model.Elevator;
import com.example.elevatorsimulation.repository.ElevatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.Math.abs;

@Service
@AllArgsConstructor
public class ElevatorSearchService {
    private ElevatorRepository elevatorRepository;

    public Elevator find(int fillingFloor, Direction direction) {
        Elevator elevator = null;
        if (direction == Direction.UP) {
            elevator = findGetUpDirectionElevator(fillingFloor);
        } else if (direction == Direction.DOWN) {
            elevator = findGetDownDirectionElevator(fillingFloor);
        }

        if (Objects.isNull(elevator)) {
            elevator = elevatorRepository.checkIsTransfer(fillingFloor, direction);
            if (Objects.isNull(elevator)) {
                elevator = findGetFreeElevator(fillingFloor, direction);
            }
        }
        return elevator;
    }

    private Elevator findGetFreeElevator(int fillingFloor, Direction direction) {
        Elevator elevator = null;
        int distance = Integer.MAX_VALUE;
        for (Elevator e : elevatorRepository.getFreeElevators()) {
            if (e.getCurrentFloor() == fillingFloor) {
                initialFreeElevator(e, direction);
                return e;
            }
            if (abs(distance) > abs(fillingFloor - e.getCurrentFloor())) {
                distance = fillingFloor - e.getCurrentFloor();
                elevator = e;
            }
        }

        if (Objects.isNull(elevator)) {
            return null;
        }

        elevatorRepository.getFreeElevators().remove(elevator);
        elevatorRepository.addToTransfer(elevator);
        elevator.setDirection(direction);
        elevator.setTargetFloor(fillingFloor);
        return elevator;
    }

    private Elevator findGetUpDirectionElevator(int fillingFloor) {
        Elevator elevator = null;
        int distance = Integer.MAX_VALUE;
        for (Elevator e : elevatorRepository.getElevatorsGoUp()) {
            if (e.getCurrentFloor() == fillingFloor) {
                return e;
            }
            if (fillingFloor > e.getCurrentFloor() && distance > fillingFloor - e.getCurrentFloor()) {
                distance = fillingFloor - e.getCurrentFloor();
                elevator = e;
            }
        }
        return elevator;
    }

    private Elevator findGetDownDirectionElevator(int fillingFloor) {
        Elevator elevator = null;
        int distance = Integer.MAX_VALUE;
        for (Elevator e : elevatorRepository.getElevatorsGoDown()) {
            if (e.getCurrentFloor() == fillingFloor) {
                return e;
            }

            if (fillingFloor < e.getCurrentFloor() && distance > e.getCurrentFloor() - fillingFloor) {
                distance = e.getCurrentFloor() - fillingFloor;
                elevator = e;
            }
        }
        return elevator;
    }

    private void initialFreeElevator(Elevator elevator, Direction direction) {
        elevatorRepository.getFreeElevators().remove(elevator);
        if (direction == Direction.UP) {
            elevatorRepository.getElevatorsGoUp().add(elevator);
            elevator.setDirection(Direction.UP);
        } else if (direction == Direction.DOWN) {
            elevatorRepository.getElevatorsGoDown().add(elevator);
            elevator.setDirection(Direction.DOWN);
        }
    }

}
