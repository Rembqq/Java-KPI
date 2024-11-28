package org.example.lab4.vehicles;

import org.example.lab4.people.Passenger;

public class Bus extends Vehicle<Passenger> {
    public Bus() {
        super(36); // 36 - max seats
    }
}
