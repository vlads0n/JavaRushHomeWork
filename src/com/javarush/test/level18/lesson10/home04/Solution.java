package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
        FileInputStream file1InputStream = new FileInputStream(file1Name);
        ArrayList<Integer> list = new ArrayList<Integer>();

        while (file1InputStream.available() > 0)
        {
            list.add(file1InputStream.read());
        }
        file1InputStream.close();

        FileOutputStream file1OutputStream = new FileOutputStream(file1Name);
        FileInputStream file2 = new FileInputStream(file2Name);

        while (file2.available() > 0)
        {
            byte[] buffer = new byte [file2.available()];
            int count = file2.read(buffer);
            file1OutputStream.write(buffer, 0, count);
        }

        for (int i = 0; i < list.size(); i++)
        {
            file1OutputStream.write(list.get(i));
        }

        reader.close();
        file1OutputStream.close();
        file2.close();

    }
}
