package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter extends FileWriter {

    public FileConsoleWriter(String fileName) throws IOException {
        super(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        super(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        super(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        super(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        super(fd);
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c);
        char[] chars = new char[]{(char) c};
        for (char i : chars)
            System.out.print(i);
        System.out.println();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        super.write(cbuf, off, len);
        for (int i = 0; i < len; i++)
            System.out.print(cbuf[i + off]);
        System.out.println();
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        super.write(str, off, len);
        for (int i = 0; i < len; i++)
            System.out.print(str.toCharArray()[i + off]);
        System.out.println();
    }
}
