package com.example.elevatorsimulation.service;

import com.example.elevatorsimulation.model.Direction;
import com.example.elevatorsimulation.model.Elevator;
import com.example.elevatorsimulation.model.WaiterInfo;
import com.example.elevatorsimulation.model.StateDto;
import com.example.elevatorsimulation.controller.ElevatorSystemInterface;
import com.example.elevatorsimulation.repository.ElevatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ElevatorSupervisor implements ElevatorSystemInterface {
    private ElevatorSearchService elevatorSearchService;
    private ElevatorRepository elevatorRepository;
    private ElevatorMoveService elevatorMoveService;
    private List<WaiterInfo> waitingQueue = new ArrayList();

    @Override
    public void createElevator(int numberFloors) {
        elevatorRepository.createElevator(numberFloors);
    }

    @Override
    public void pickup(int fillingFloor, int direction) {
        Direction d = direction > 0 ? Direction.UP : Direction.DOWN;
        Elevator elevator = elevatorSearchService.find(fillingFloor, d);
        if (Objects.isNull(elevator))
            waitingQueue.add(new WaiterInfo(fillingFloor, d));
        else
            elevator.addAnotherFloor(elevator.getCurrentFloor(), fillingFloor);
    }

    @Override
    public void update(int idElevator, int currentFloor, int targetFloor) {
        Elevator elevator = elevatorRepository.findElevatorById(idElevator);
        elevator.addAnotherFloor(currentFloor, targetFloor);
    }

    @Override
    public void step() {
        elevatorMoveService.moveElevators();
        serviceWaitersQueue();
    }

    @Override
    public List<StateDto> status() {
        return elevatorRepository.getElevatorsState();
    }

    private void serviceWaitersQueue() {
        List<WaiterInfo> lastWaiters = List.copyOf(waitingQueue);
        waitingQueue.clear();
        lastWaiters.forEach(waiters -> this.pickup(waiters.getFillingFloor(), waiters.getDirection().getDirectionValue()));
    }
}
