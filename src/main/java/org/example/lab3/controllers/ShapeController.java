package org.example.lab3.controllers;

import org.example.lab3.model.Rectangle;
import org.example.lab3.model.Shape;
import org.example.lab3.model.Triangle;
import org.example.lab3.view.ShapeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShapeController {
    private List<Shape> shapes;
    private ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
        this.shapes = new ArrayList<>();
    }

    public void generateShapes() {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        Random random = new Random();
        for(int i = 0; i < 10; ++i) {
            String color = colors[random.nextInt(colors.length)];
            switch(random.nextInt(3)) {
                case 0 -> shapes.add(new Rectangle(color,
                        random.nextDouble() * 10 + 1, random.nextDouble() * 10 + 1));
                case 1 -> shapes.add(new Triangle(color, random.nextDouble() * 10 + 1,
                        random.nextDouble() * 10 + 1));

            }
        }
    }
}















