package org.example.lab5;

public class StreamEncryptor {
    private final char key;

    public StreamEncryptor(char key) {
        this.key = key;
    }



    public static void main(String[] args) {
        StreamEncryptor encryptor = new StreamEncryptor('5');
    }

}
