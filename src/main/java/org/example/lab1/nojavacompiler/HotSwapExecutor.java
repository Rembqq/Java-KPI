package org.example.lab1.nojavacompiler;

public class HotSwapExecutor {
    public static void main(String[] args) throws Exception {
        String classDir = "D:\\Coding Projects\\IJ-workspace\\Java KPI\\src\\main\\java\\";
        //String classDir = "src.main.java.org.example.lab1.";
        //String className = "TestModule";
        String className = "org.example.lab1.TestModule";
        String classFilePath = classDir + className.replace('.', '/') + ".class";;

        ClassFileWatcher watcher = new ClassFileWatcher(classFilePath);

        System.out.println("Watching for changes in: " + classFilePath);

        while (true) {
            if (watcher.hasChanged()) {
                System.out.println("Class file changed, reloading...");


                HotSwapClassLoader classLoader = new HotSwapClassLoader(classDir);
                System.out.println("OK");
                Class<?> clazz = classLoader.loadClass(className);
                System.out.println("OK");

                Object t = clazz.getDeclaredConstructor().newInstance();
                System.out.println(t);
            } else {
                System.out.println("No change detected");
            }
            Thread.sleep(5000);
        }
    }
}
