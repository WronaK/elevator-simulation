package com.example.elevatorsimulation.service;

import com.example.elevatorsimulation.model.Direction;
import com.example.elevatorsimulation.model.Elevator;
import com.example.elevatorsimulation.repository.ElevatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class ElevatorMoveService {
    ElevatorRepository elevatorRepository;

    public void moveElevators() {
        moveElevatorsDown();
        moveElevatorsUp();
        moveElevatorsTransfer();
    }

    private void moveElevatorsTransfer() {
        List<Elevator> toDelete = new LinkedList<>();
        for (Elevator elevator : elevatorRepository.getElevatorsTransfer()) {
            elevator.transfer();
            if (elevator.getCurrentFloor() == elevator.getTargetFloor()) {
                elevator.removeTransferFloor();
                toDelete.add(elevator);
            }
        }

        for (Elevator elevator : toDelete) {
            elevatorRepository.removeFromTransfer(elevator);
            elevatorRepository.registerElevatorDirection(elevator, elevator.getDirection());
        }
    }

    private void moveElevatorsDown() {
        List<Elevator> toDelete = new LinkedList<>();
        for (Elevator elevator : elevatorRepository.getElevatorsGoDown()) {
            elevator.moveDown();
            if (elevator.getDirection() != Direction.DOWN) {
                toDelete.add(elevator);
            }
        }

        for (Elevator elevator : toDelete) {
            elevatorRepository.changeDirectionMove(elevator, Direction.DOWN, elevator.getDirection());
        }
    }

    private void moveElevatorsUp() {
        List<Elevator> toDelete = new LinkedList<>();

        for (Elevator elevator : elevatorRepository.getElevatorsGoUp()) {
            elevator.moveUp();
            if (elevator.getDirection() != Direction.UP) {
                toDelete.add(elevator);
            }
        }
        for (Elevator elevator : toDelete) {
            elevatorRepository.changeDirectionMove(elevator, Direction.UP, elevator.getDirection());
        }
    }
}
