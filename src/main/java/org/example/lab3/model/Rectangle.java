package org.example.lab3.model;

import java.io.Serial;
import java.io.Serializable;

public class Rectangle extends Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final double width;
    private final double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle...");
    }
}
