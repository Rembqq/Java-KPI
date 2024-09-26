package org.example.lab1.nojavacompiler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestModule {
    @Override
    public String toString() {
        return "TestModule оо!";
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        TestModule t = new TestModule();
        System.out.println(t);
        URLClassLoader userDefinedCLassLoader = new URLClassLoader(new URL[]{new URL("file://home/Nikita/MyJar.jar")});
        Class<?> cl = userDefinedCLassLoader.loadClass("myClassname");

//        Thread th = Thread.currentThread();
//        ClassLoader = th.getContextClassLoader();
    }
}

class MyLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[0];
        try{
            bytes = Files.readAllBytes(Paths.get("fileName"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        Class<?> cl = defineClass(name, bytes, 0, bytes.length);
        return cl;
    }
}
