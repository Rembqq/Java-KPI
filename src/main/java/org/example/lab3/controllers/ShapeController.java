package org.example.lab3.controllers;

import org.example.lab3.model.Circle;
import org.example.lab3.model.Rectangle;
import org.example.lab3.model.Shape;
import org.example.lab3.model.Triangle;
import org.example.lab3.view.ShapeView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ShapeController {
    private final List<Shape> shapes;
    private final ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
        this.shapes = new ArrayList<>();
    }

    public void generateShapes() {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        Random random = new Random();
        int shapesAmount = 10;
        for(int i = 0; i < shapesAmount; ++i) {
            String color = colors[random.nextInt(colors.length)];
            switch(random.nextInt(3)) {
                case 0 -> shapes.add(new Rectangle(color,
                        random.nextDouble() * 10 + 1, random.nextDouble() * 10 + 1));
                case 1 -> shapes.add(new Triangle(color, random.nextDouble() * 10 + 1,
                        random.nextDouble() * 10 + 1));
                case 2 -> shapes.add(new Circle(color, random.nextDouble() * 10 + 1));
            }
        }
    }

    public void displayChanges() {
        view.displayChanges(shapes);
    }

    public void calculateTotalArea() {
        double totalArea = shapes.stream().mapToDouble(Shape::calcArea).sum();
        view.displayTotalArea(totalArea);
    }

    public void calculateTotalAreaByType(String shapeType) {
        double totalArea = shapes.stream()
                .filter(shape -> shape.getClass().getSimpleName().equalsIgnoreCase(shapeType))
                .mapToDouble(Shape::calcArea)
                .sum();
        view.displayFilteredArea(shapeType, totalArea);
    }

    public void sortShapesByArea() {
        shapes.sort(Comparator.comparingDouble(Shape::calcArea));
    }

    public void sortShapesByColor() {
        shapes.sort(Comparator.comparing(Shape::getShapeColor));
    }

}















