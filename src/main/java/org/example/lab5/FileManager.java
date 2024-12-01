package org.example.lab5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    public String getFileName(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public void validateFile(String fileName) {
        File file = new File(fileName);
        if(!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Файл не існує або не є файлом: " + fileName);
        }
    }

    public void validateOutputFile(String fileName) {
        File file = new File(fileName);
        try {
            if(file.exists()) {
                System.out.println("Увага: файл " + fileName + " буде перезаписано.");
            } else if(!file.createNewFile()) {
                throw new IOException("Не вдалося створити файл: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Помилка при підготовці файлу: " + fileName, e);
        }
    }

}
