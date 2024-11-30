package org.example.lab3.model;

import java.io.Serial;
import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
    private final String shapeColor;
    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() { // Додаємо гетер
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return String.format("%s [Color: %s, Area: %.2f]", this.getClass().getSimpleName(), shapeColor, calcArea());
    }
}
