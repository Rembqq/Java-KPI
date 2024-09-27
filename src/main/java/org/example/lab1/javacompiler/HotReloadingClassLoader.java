/*
package org.example.lab1.javacompiler;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class HotReloadingClassLoader extends ClassLoader {
    private String classPath;

    public HotReloadingClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if(classData == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadClassData(String className) {
        String fileName = classPath + className.replace(".", "/") + ".class";
        try(FileInputStream fis = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                int byteValue;
                while((byteValue = fis.read()) != -1) {
                    baos.write();
            }
        } catch (IOException e) {

        }
    }

}
*/
