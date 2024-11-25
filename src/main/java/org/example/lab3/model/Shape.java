package org.example.lab3.model;

public abstract class Shape implements Drawable {
    protected String shapeColor;
    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return String.format("%s [Color: %s, Area: %.2f]", this.getClass().getSimpleName(), shapeColor, calcArea());
    }
}
