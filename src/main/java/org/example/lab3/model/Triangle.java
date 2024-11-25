package org.example.lab3.model;

public class Triangle extends Shape {
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
