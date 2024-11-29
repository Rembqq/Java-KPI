package org.example.lab5;

import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class MaxWordStringFromFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputPath = scanner.nextLine();
        try {
            FileToObjectStreamProcessor fileProcessor = new FileToObjectStreamProcessor(inputPath);

            // Створюємо об'єктний файл
            fileProcessor.createObjectFile();

            // Зчитуємо рядок з максимальною кількістю слів
            String lineWithMaxWords = fileProcessor.findLineWithMaxWords();
            System.out.println("Рядок з максимальною кількістю слів: " + lineWithMaxWords);
        } catch (NoSuchFileException e) {
            System.err.println("Вказано невірний шлях до файлу: " + e.getMessage());
        }
    }
}
