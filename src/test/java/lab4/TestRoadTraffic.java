package lab4;

import org.example.lab4.people.Firefighter;
import org.example.lab4.people.Passenger;
import org.example.lab4.people.Policeman;
import org.example.lab4.people.RegularPassenger;
import org.example.lab4.vehicles.Bus;
import org.example.lab4.vehicles.Taxi;
import org.example.lab4.vehicles.FireEngine;
import org.example.lab4.vehicles.PoliceCar;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestRoadTraffic {

    public String[] names = {"John", "Mike", "Alice", "Emma", "Jake", "Tom",
                        "Wylie", "Valery", "Umar", "McLay", "Uzay"};

    @Test
    void testMaxBus() {

        // Preparation
        Bus bus = new Bus();
        bus.addPassenger(new RegularPassenger(names[0]));
        bus.addPassenger(new Firefighter(names[1]));
        bus.addPassenger(new Policeman(names[2]));
        bus.addPassenger(new RegularPassenger(names[3]));
        bus.addPassenger(new Policeman(names[4]));
        bus.addPassenger(new Firefighter(names[5]));
        bus.addPassenger(new RegularPassenger(names[6]));
        bus.addPassenger(new RegularPassenger(names[7]));
        bus.addPassenger(new Policeman(names[8]));
        bus.addPassenger(new Policeman(names[9]));

        // Test

        assertThrows(IllegalStateException.class, () -> {
            bus.addPassenger(new Policeman(names[10]));
        }, "Not enough seats");
    }

    @Test
    void testMaxTaxi() {

        // Preparation


    }

    @Test
    void testRemovePassenger() {

        // Preparation
        Bus bus = new Bus();
        RegularPassenger passenger = new RegularPassenger(names[0]);

        bus.addPassenger(passenger);

        bus.removePassenger(passenger);

        assertEquals(0, bus.getTakenSeats());

        // Test
        assertThrows(IllegalStateException.class, () -> {
            bus.removePassenger(passenger);
        }, "This fellow is not inside");
    }

    @Test
    void testMaxPoliceCar() {

        // Preparation
        PoliceCar policeCar = new PoliceCar();

        policeCar.addPassenger(new Policeman(names[0]));
        policeCar.addPassenger(new Policeman(names[1]));
        policeCar.addPassenger(new Policeman(names[2]));
        policeCar.addPassenger(new Policeman(names[3]));
        policeCar.addPassenger(new Policeman(names[4]));

        // Test

        assertThrows(IllegalStateException.class, () -> {
            policeCar.addPassenger(new Policeman(names[6]));
        }, "Not enough seats");

//        assertThrows(IllegalArgumentException.class, () -> {
//            policeCar.addPassenger(new Firefighter(names[7]));
//        }, "Only policemen are allowed in police cars");
    }

    @Test
    void testMaxFireEngine() {

        // Preparation
        FireEngine fireEngine = new FireEngine();

        fireEngine.addPassenger(new Firefighter(names[0]));
        fireEngine.addPassenger(new Firefighter(names[1]));
        fireEngine.addPassenger(new Firefighter(names[2]));
        fireEngine.addPassenger(new Firefighter(names[3]));
        fireEngine.addPassenger(new Firefighter(names[4]));
        fireEngine.addPassenger(new Firefighter(names[5]));
        fireEngine.addPassenger(new Firefighter(names[6]));

        // Test

        assertThrows(IllegalStateException.class, () -> {
            fireEngine.addPassenger(new Firefighter(names[8]));
        }, "Not enough seats");
    }

    @Test
    void testMinBus() {

        // Preparation
        Bus bus = new Bus();
        bus.removePassenger(new Policeman(names[0]));

        // Test
        assertThrows(IllegalStateException.class, () -> {
            bus.addPassenger(new Policeman(names[10]));
        }, "This fellow is not inside");
    }

}
