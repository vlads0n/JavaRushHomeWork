package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки ввода-вывода
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputFile1 = new FileInputStream(reader.readLine());
        FileOutputStream outputFile2 = new FileOutputStream(reader.readLine());
        FileOutputStream outputFile3 = new FileOutputStream(reader.readLine());
        reader.close();

        int count = inputFile1.available();
        byte[] buffer = new byte[(count/2 + count%2)];

        while (inputFile1.available() > 0)
        {
            if (inputFile1.available() > buffer.length)
            {
                inputFile1.read(buffer);
                outputFile2.write(buffer);
            }
            else
            {
                int count1 = inputFile1.read(buffer);
                outputFile3.write(buffer, 0, count1);
            }
        }

        inputFile1.close();
        outputFile2.close();
        outputFile3.close();
    }
}
