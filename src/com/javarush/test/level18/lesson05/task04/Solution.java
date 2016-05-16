package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки ввода-вывода
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputFile1 = new FileInputStream(reader.readLine());
        FileOutputStream outputFile2 = new FileOutputStream(reader.readLine());
        reader.close();

        byte[] buffer = new byte[1000];
        while (inputFile1.available() > 0)
        {
            int count = inputFile1.read(buffer);
            for (int i = (count - 1); i >= 0; i--)
            {
                outputFile2.write(buffer[i]);
            }
        }

        inputFile1.close();
        outputFile2.close();
    }
}
