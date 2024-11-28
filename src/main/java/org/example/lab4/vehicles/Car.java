package org.example.lab4.vehicles;

import org.example.lab4.people.Passenger;

public abstract class Car<T extends Passenger> extends Vehicle<T> {
    public Car(int maxSeats) {
        super(maxSeats);
    }
}
