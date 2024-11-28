package org.example.lab4.vehicles;

import org.example.lab4.people.Passenger;

import java.util.List;

abstract class Vehicle<T extends Passenger> {

    private int maxSeats;
    private final List<T> passengers;

    public Vehicle(List<T> passengers) {
        this.passengers = passengers;
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

    public

}
