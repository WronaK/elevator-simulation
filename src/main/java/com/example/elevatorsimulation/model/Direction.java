package com.example.elevatorsimulation.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {
    UP(1), DOWN(-1), NONE(0);

    private final int direction;

    public int getDirectionValue(){
        return direction;
    }
}
