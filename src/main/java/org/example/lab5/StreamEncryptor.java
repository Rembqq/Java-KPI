package org.example.lab5;

import java.io.*;
import java.util.Scanner;

public class StreamEncryptor {
    private final char key;

    public StreamEncryptor(char key) {
        this.key = key;
    }

    private static class EncryptionOutputStream extends FilterOutputStream {
        private final char key;
        public EncryptionOutputStream(OutputStream out, char key) {
            super(out);
            this.key = key;
        }

        @Override
        public void write(int b) throws IOException {
            super.write(b + key);
        }
    }

    private static class DecryptionInputStream extends FilterInputStream {
        private final char key;
        public DecryptionInputStream(InputStream in, char key) {
            super(in);
            this.key = key;
        }

        @Override
        public int read() throws IOException {
            int b = super.read();
            return (b == -1) ? -1 : b - key;
        }
    }

    public void encrypt(String inputFile, String outputFile) throws IOException {
        try(FileInputStream fis = new FileInputStream(inputFile);
            EncryptionOutputStream eos = new EncryptionOutputStream(new FileOutputStream(outputFile), key))
            {
            int b;
            while((b = fis.read()) != -1) {
                eos.write(b);
            }
        }
    }

    public void decrypt(String inputFile, String outputFile) throws IOException {
        try(DecryptionInputStream dis = new DecryptionInputStream(new FileInputStream(inputFile), key);
            FileOutputStream fos = new FileOutputStream(outputFile)) {
            int b;
            while ((b = dis.read()) != -1) {
                fos.write(b);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        StreamEncryptor encryptor = new StreamEncryptor('d');

        try {
            String inputFile = fileManager.getFileName("Enter input name: ");
            fileManager.validateFile(inputFile);
            String encryptedFile = fileManager.getFileName("Enter encrypted file name: ");
            fileManager.validateOutputFile(encryptedFile);

            String decryptedFile = fileManager.getFileName("Enter decrypted name: ");
            fileManager.validateOutputFile(decryptedFile);

            encryptor.encrypt(inputFile, encryptedFile);
            System.out.println("Файл зашифровано!");

            encryptor.decrypt(encryptedFile, decryptedFile);
            System.out.println("Файл розшифровано!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
