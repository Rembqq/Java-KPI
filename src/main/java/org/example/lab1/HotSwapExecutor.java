package org.example.lab1;

public class HotSwapExecutor {
    public static void main(String[] args) throws Exception {
        String classDir = "target/classes/org/example/lab1/";
        String className = "TestModule";
        String classFilePath = classDir + className + ".class";

        ClassFileWatcher watcher = new ClassFileWatcher(classFilePath);

        while (true) {
            if (watcher.hasChanged()) {
                System.out.println("Class file changed, reloading...");


                HotSwapClassLoader classLoader = new HotSwapClassLoader(classDir);
                Class<?> clazz = classLoader.loadClass(className);

                Object instance = clazz.getDeclaredConstructor().newInstance();
                System.out.println(instance);
            }
            Thread.sleep(5000);
        }
    }
}
