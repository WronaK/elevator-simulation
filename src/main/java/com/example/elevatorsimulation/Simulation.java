package com.example.elevatorsimulation;

import com.example.elevatorsimulation.controller.ElevatorSystemInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Simulation implements CommandLineRunner {
    @Value("${app.elevator}")
    private int numberOfElevators;

    private ElevatorSystemInterface elevatorSystemInterface;

    public Simulation(ElevatorSystemInterface elevatorSystemInterface) {
        this.elevatorSystemInterface = elevatorSystemInterface;
    }

    private void startSimulation() {
        for(int i = 0; i < numberOfElevators; i++)
            elevatorSystemInterface.createElevator();
    }

    @Override
    public void run(String... args) throws Exception {
        startSimulation();
    }
}
