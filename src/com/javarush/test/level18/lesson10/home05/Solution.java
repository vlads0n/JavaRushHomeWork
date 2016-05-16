package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать во второй файл
Закрыть потоки
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter file2 = new BufferedWriter(new FileWriter(reader.readLine()));

        String s;
        String[] result = null;
        while ((s = file1.readLine()) != null)
        {
            result = s.split(" ");
        }

        for (String i: result)
        {
            double tmp = Double.parseDouble(i);
            tmp = Math.round(tmp);
            file2.write(String.valueOf((int) tmp) + " ");
        }

        reader.close();
        file1.close();
        file2.close();

    }
}
