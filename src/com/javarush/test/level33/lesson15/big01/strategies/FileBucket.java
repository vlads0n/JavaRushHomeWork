package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Влад on 28.03.2016.
 */
public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile("tmp", null);
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
                outputStream.writeObject(entry);
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() {
        if (path.toFile().length() == 0)
            return null;

        Entry entry = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            entry = (Entry) inputStream.readObject();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }
}
