package com.example.elevatorsimulation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {
    UP(1), DOWN(-1), NONE(0);

    private final int direction;
}
