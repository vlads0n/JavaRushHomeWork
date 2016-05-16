package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки ввода-вывода.

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file1 = new BufferedReader(new FileReader(reader.readLine()));
        FileWriter file2 = new FileWriter(reader.readLine());
        while (file1.ready())
        {
            String string = file1.readLine();
            String[] array = string.split(" ");
            for (int i = 0; i < array.length; i++)
            {
                try
                {
                    int number = Integer.parseInt(array[i]);
                    file2.write(number + " ");
                }
                catch (Exception e)
                {
                }
            }
        }
        reader.close();
        file1.close();
        file2.close();
    }
}
