package org.example.lab4.vehicles;

import org.example.lab4.people.RegularPassenger;

import java.util.ArrayList;
import java.util.List;

public class Taxi extends Car {

    protected final int maxSeats = 5;
    protected int takenSeats;
    public List<RegularPassenger> passengers = new ArrayList<>();

    @Override
    public int getMaxSeats() {
        return 0;
    }

    @Override
    public int getTakenSeats() {
        return 0;
    }
}
