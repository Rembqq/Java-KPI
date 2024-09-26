package org.example.lab1;

import java.io.File;

public class ClassFileWatcher {
    private final File classFile;
    private long lastModified;

    public ClassFileWatcher(String classFilePath) {
        this.classFile = new File(classFilePath);
        this.lastModified = classFile.lastModified();
    }

    public boolean hasChanged() {
        long currentModifiedTIme = classFile.lastModified();
        if(currentModifiedTIme > lastModified) {
            lastModified = currentModifiedTIme;
            return true;
        }
        return false;
    }

}
