package org.example.lab8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.*;
import java.util.regex.Pattern;

public class DirectoryWordCounter {
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length != 3) {
            System.out.println("Usage: java DirectoryWordCounter <directory> <letter> <output_file>");
            return;
        }

        String directoryPath = args[0];
        char startingLetter = args[1].charAt(0);
        String outputFilePath = args[2];

        ExecutorService executor = Executors.newCachedThreadPool();
        ConcurrentLinkedQueue<String> results = new ConcurrentLinkedQueue<>();

        Files.walkFileTree(Paths.get(directoryPath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if(file.toString().endsWith(".txt")) {
                    executor.submit(() -> processFile(file, startingLetter, results));
                }
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                executor.submit(() -> {
                    try {
                        Files.walkFileTree(dir, this);
                    } catch (IOException e) {
                        System.err.println("Error: IOException in preVisitDirectory()");
                    }
                });
                return FileVisitResult.CONTINUE;
            }
        });

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        writeResultsToFile(results, outputFilePath);
        printFileContents(outputFilePath);
    }

    private static void processFile(Path file, char startingLetter, ConcurrentLinkedQueue<String> results) {
        try(BufferedReader reader = Files.newBufferedReader(file)) {
            Pattern pattern = Pattern.compile("\\b" + startingLetter + "\\w*", Pattern.CASE_INSENSITIVE);
            long count = reader.lines()
                    .flatMap(line -> pattern.matcher(line).results())
                    .count();
            if(count > 0) {
                results.add(file + ": " + count);
            }
        } catch (IOException e) {
            System.err.println("Error: IOException in processFile()");
        }
    }

    private static void writeResultsToFile(ConcurrentLinkedQueue<String> results, String outputFilePath) {
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            for(String result : results) {
                writer.write(result);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error: IOException in writeResultsToFile()");
        }
    }

    private static void printFileContents(String filePath) {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error: IOException in printFileContents()");
        }
    }

}
