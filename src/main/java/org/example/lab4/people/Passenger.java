package org.example.lab4.people;

public abstract class Passenger {
    private final String name;
    public Passenger(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
