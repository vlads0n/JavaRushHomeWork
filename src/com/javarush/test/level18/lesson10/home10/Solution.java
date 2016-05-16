package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть все потоки ввода-вывода
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/


import java.io.*;
import java.util.Set;
import java.util.TreeSet;


public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new TreeSet<String>();
        String s;
        String fileName = "";
        String fileValue = "";

        while (!(s = reader.readLine()).equals("end"))
        {
            set.add(s);
            String [] split = s.split(".part");
            fileName = split[0];
            String a = split[1];
            fileValue = a.substring(1, a.length());
        }
        reader.close();

        String file = fileName + fileValue;
        File name = new File(file);
        FileOutputStream outputStream = new FileOutputStream(name);
        FileInputStream inputStream = null;

        for (String i: set)
        {
            inputStream = new FileInputStream(i);
            byte[] buffer = new byte[inputStream.available()];
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count);

        }
        inputStream.close();
        outputStream.close();
    }
}
