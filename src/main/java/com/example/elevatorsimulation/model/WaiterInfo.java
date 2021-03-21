package com.example.elevatorsimulation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WaiterInfo {
    private int fillingFloor;
    private Direction direction;
}
