package org.example.lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HotSwapClassLoader extends ClassLoader{
    private final String classDir;

    public HotSwapClassLoader(String classDir) {
        this.classDir = classDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classFilePath = classDir + name.replace('.', '/') + ".class";
        File classFile = new File(classFilePath);

        if(!classFile.exists()) {
            throw new ClassNotFoundException("Class not found: " + name);
        }
        try(FileInputStream fis = new FileInputStream(classFile)) {
            byte[] classBytes = new byte[(int) classFile.length()];
            fis.read(classBytes);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e){
            throw new ClassNotFoundException("Failed to load class: " + name, e);
        }

    }

}
