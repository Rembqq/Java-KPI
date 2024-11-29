package org.example.lab5;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class FileToObjectStreamProcessor {
    private final String inputPath;
    private final String objectFilePath;

    public FileToObjectStreamProcessor(String inputPath) throws NoSuchFileException {
        this.inputPath = normalizePath(inputPath);

        // Визначаємо шлях для файлу об'єктного потоку
        int lastSlashIndex = this.inputPath.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            throw new NoSuchFileException("Шлях не містить роздільників.");
        }
        String prefix = inputPath.substring(0, lastSlashIndex + 1);
        this.objectFilePath = prefix + "serialized_lines.dat";
    }

    public void createObjectFile() {
        try (
                Scanner scanner = new Scanner(new File(inputPath));
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(objectFilePath));
        ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                oos.writeObject(line);
            }
            System.out.println("Дані успішно серіалізовані у файл: " + objectFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String findLineWithMaxWords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(objectFilePath))) {
            String lineWithMaxWords = "";
            int maxWordCount = 0;
            while (true) {
                try {
                    String line = (String) ois.readObject();
                    int wordCount = line.split("\\s+").length;
                    if (wordCount > maxWordCount) {
                        maxWordCount = wordCount;
                        lineWithMaxWords = line;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            return lineWithMaxWords;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String normalizePath(String path) {
        if(path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        if(!path.contains("/")) {
            return "./" + path;
        }
        return path;
    }

}
