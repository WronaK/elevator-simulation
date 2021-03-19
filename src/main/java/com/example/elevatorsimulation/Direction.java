package com.example.elevatorsimulation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {
    UP(1), DOWN(-1);

    private final int direction;
}
