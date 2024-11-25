package org.example.lab3.view;

import org.example.lab3.model.Shape;

import java.util.List;

public class ShapeView {
    public void displayChanges(List<Shape> shapes) {
        shapes.forEach(System.out::println);
    }

    public void displayTotalArea(double totalArea) {
        System.out.printf("Total Area: %.2f\n", totalArea);
    }

    public void displayFilteredArea(String shapeType, double totalArea) {
        System.out.printf("Total Area of %s: %.2f\n", shapeType, totalArea);
    }
}
