package org.example.lab3.controllers;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.example.lab3.model.Circle;
import org.example.lab3.model.Rectangle;
import org.example.lab3.model.Shape;
import org.example.lab3.model.Triangle;
import org.example.lab3.view.ShapeView;

import java.io.*;
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
        int shapesAmount = 2;
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

    public void saveObjectsToFile() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("shapes.dat"))) {
            oos.writeObject(shapes);
            System.out.println("Об'єкти збережено у файл shapes.dat.");
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void getObjectsFromFile() {
//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("shapes.dat"))) {
//            System.out.println(shapes);
//            System.out.println("Point 1");
//            List<Shape> loadedShapes = (List<Shape>) ois.readObject();
//            System.out.println("Point 2");
//            shapes.clear();
//            System.out.println("Point 3");
//            shapes.addAll(loadedShapes);
//            System.out.println("Об'єкти завантажено з файлу shapes.dat.");
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Помилка читання з файлу: " + e.getMessage());
//        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("shapes.dat"))) {
            System.out.println("Загрузка объектов...");
            List<Shape> loadedShapes = (List<Shape>) ois.readObject();
            shapes.clear();
            shapes.addAll(loadedShapes);
            System.out.println("Об'єкти завантажено з файлу shapes.dat.");
        } catch (InvalidClassException e) {
            System.err.println("Версия класса не совпадает: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Помилка читання з файлу: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Клас не знайдено: " + e.getMessage());
        }
    }

    public void sortShapesByColor() {
        shapes.sort(Comparator.comparing(Shape::getShapeColor));
    }

}















