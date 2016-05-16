package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class FileConsoleWriter extends FileWriter
{
    private FileWriter fileWriter;

    public FileConsoleWriter (String fileName) throws IOException
    {
        super(fileName);
        this.fileWriter = new FileWriter(fileName);
    }

    @Override
    public void write(int c) throws IOException
    {
        super.write(c);
    }

    @Override
    public void write(char[] cbuf) throws IOException
    {
        super.write(cbuf);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        super.write(cbuf, off, len);
    }

    @Override
    public void write(String str) throws IOException
    {
        super.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {
        super.write(str, off, len);
    }

    @Override
    public Writer append(char c) throws IOException
    {
        return super.append(c);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException
    {
        return super.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException
    {
        return super.append(csq, start, end);
    }

}
