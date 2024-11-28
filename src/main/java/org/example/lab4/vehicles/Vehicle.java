package org.example.lab4.vehicles;

import org.example.lab4.people.Passenger;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Passenger> {

    private final int maxSeats;
    private final List<T> passengers;

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
        this.passengers = new ArrayList<>();
    }

    public int getMaxSeats() {
        return maxSeats;
    }
    public int getTakenSeats() {
        return passengers.size();
    }

    public void addPassenger(T passenger) {
        if(passengers.size() >= maxSeats) {
            throw new IllegalStateException("Not enough seats");
        }
        passengers.add(passenger);
    }

    public void removePassenger(T passenger) {
        if(!passengers.contains(passenger)) {
            throw new IllegalStateException("This fellow is not inside");
        }
        passengers.remove(passenger);
    }

}
