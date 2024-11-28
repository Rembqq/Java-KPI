package org.example.lab4;

import org.example.lab4.people.Passenger;
import org.example.lab4.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Passenger>> carsInRoad = new ArrayList<>();
    public int getCountOfHumans(){
        int totalPassengers = 0;
        for(Vehicle<? extends Passenger> v : carsInRoad) {
            //totalPassengers += carsInRoad.get(i).getTakenSeats();
            totalPassengers += v.getTakenSeats();
        }
        return totalPassengers;
    }
    public void addCarToRoad(Vehicle<? extends Passenger> v){
        carsInRoad.add(v);
    }
}
