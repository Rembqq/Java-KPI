package org.example.lab6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EngToUa {
    private final Map<String, String> dictionary;

    public EngToUa() {
        dictionary  = new HashMap<>();
    }

    public void addWord(String key, String val) {
        dictionary.put(key.toLowerCase(), val.toLowerCase());
    }

    public String translate(String phrase) {
        StringBuilder translated = new StringBuilder();
        String[] words = phrase.split(" ");
        for(String word : words) {
            String translatedWord = dictionary.get(word);
            if(word != null) {
                translated.append(translatedWord).append(" ");
            } else {
                translated.append("[" + word + "]").append(" ");
            }
        }
        return translated.toString().trim();
    }

    public static void main(String[] args) {
        EngToUa translator = new EngToUa();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the words you want to add");
        for(; ;) {
            System.out.println("Enter an English word (or 'exit') to finish: ");
            String engWord = scanner.nextLine();
            if(engWord.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter the translation in Ukrainian: ");
            String uaWord = scanner.nextLine();
            translator.addWord(engWord, uaWord);
            System.out.println("The word has been added.\n");
        }

        System.out.println("\nThe dictionary is ready, type the words you want to translate ");

        for(; ;) {
            System.out.println("Enter an English phrase (or 'exit') to finish: ");
            String phrase = scanner.nextLine();
            if(phrase.equalsIgnoreCase("exit")) {
                break;
            }

            String translation = translator.translate(phrase);
            System.out.println("Translation: " + translation);
        }
        scanner.close();
    }

}
