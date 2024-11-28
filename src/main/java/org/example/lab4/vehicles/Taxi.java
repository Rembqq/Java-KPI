package org.example.lab4.vehicles;

import org.example.lab4.people.Passenger;

public class Taxi extends Car<Passenger> {
    public Taxi() {
        super(5); // 5 - max seats
    }
}
