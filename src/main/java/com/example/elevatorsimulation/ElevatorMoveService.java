package com.example.elevatorsimulation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElevatorMoveService {
    ElevatorRepository elevatorRepository;

    public void moveElevators() {
        moveElevatorsDown();
        moveElevatorsUp();
    }

    private void moveElevatorsDown() {
        for(Elevator elevator: elevatorRepository.getElevatorsGoDown()) {
            elevator.moveDown();
            Direction direction = elevator.getDirection();
            if(direction != Direction.DOWN) {
                elevatorRepository.changeDirectionMove(elevator, Direction.DOWN, direction);
            }
        }
    }

    private void moveElevatorsUp() {
        for(Elevator elevator: elevatorRepository.getElevatorsGoUp()) {
            elevator.moveUp();
            Direction direction = elevator.getDirection();
            if(direction != Direction.UP) {
                elevatorRepository.changeDirectionMove(elevator, Direction.UP, direction);
            }
        }
    }
}
