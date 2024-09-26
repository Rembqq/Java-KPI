package org.example.lab1.nojavacompiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HotSwapClassLoader extends ClassLoader{
    private final String classPath;

    public HotSwapClassLoader(String classDir) {
        this.classPath = classDir;
    }

//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String classFilePath = classDir + name.replace('.', '/') + ".class";
//        File classFile = new File(classFilePath);
//
//        if(!classFile.exists()) {
//            throw new ClassNotFoundException("Class not found: " + name);
//        }
//        try(FileInputStream fis = new FileInputStream(classFile)) {
//            byte[] classBytes = new byte[(int) classFile.length()];
//            fis.read(classBytes);
//            return defineClass(name, classBytes, 0, classBytes.length);
//        } catch (IOException e){
//            throw new ClassNotFoundException("Failed to load class: " + name, e);
//        }
//
//    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
    try {
        // Пакетні імена мають бути замінені на системні шляхи
        String fileName = classPath + name.replace('.', '/') + ".class";
        System.out.println("Loading class from: " + fileName);

        byte[] classBytes = Files.readAllBytes(Paths.get(fileName));
        return defineClass(name, classBytes, 0, classBytes.length);
    } catch (IOException e) {
        e.printStackTrace();
        throw new ClassNotFoundException(name);
    }
}

}
