package org.example.lab4.vehicles;

import org.example.lab4.people.Passenger;
import org.example.lab4.people.RegularPassenger;

import java.util.ArrayList;
import java.util.List;

public class Taxi extends Car<Passenger> {
    public Taxi() {
        super(5); // 5 - max seats
    }
}
