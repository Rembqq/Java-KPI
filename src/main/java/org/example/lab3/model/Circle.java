package org.example.lab3.model;

import java.io.Serial;
import java.io.Serializable;

public class Circle extends Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private final double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle...");
    }

}