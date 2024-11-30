package org.example.lab3;

import org.example.lab3.controllers.ShapeController;
import org.example.lab3.view.ShapeView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShapeView shapeView = new ShapeView();
        ShapeController shapeController = new ShapeController(shapeView);

        shapeController.generateShapes();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("""
                    \nMenu:
                    1. Display Shapes
                    2. Calculate Total Area
                    3. Calculate Total Area by Shape Type
                    4. Sort Shapes by Area
                    5. Sort Shapes by Color
                    6. Save objects to file
                    7. Get objects from file
                    8. Exit
                    """);
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> shapeController.displayChanges();
                case 2 -> shapeController.calculateTotalArea();
                case 3 -> {
                    System.out.println("Enter Shape Type (Rectangle/Triangle/Circle): ");
                    String shapeType = scanner.next();
                    shapeController.calculateTotalAreaByType(shapeType);
                }
                case 4 -> {
                    shapeController.sortShapesByArea();
                    System.out.println("Shapes sorted by area.");
                }
                case 5 -> {
                    shapeController.sortShapesByColor();
                    System.out.println("Shapes sorted by color.");
                }
                case 6 -> shapeController.saveObjectsToFile();
                case 7 -> shapeController.getObjectsFromFile();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Wrong option");
            }
        }

    }
}
