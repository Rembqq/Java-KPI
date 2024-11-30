package org.example.lab3.model;

import java.io.Serial;
import java.io.Serializable;

public class Triangle extends Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private final double base;
    private final double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return (base * height) / 2;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a triangle...");
    }
}
