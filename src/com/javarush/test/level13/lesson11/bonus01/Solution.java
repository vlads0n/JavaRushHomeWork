package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/


import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader readFile = new BufferedReader(new FileReader(reader.readLine()));

        ArrayList <Integer> array = new ArrayList <Integer>();
        ArrayList <Integer> array2 = new ArrayList <Integer>();

        reader.close();

        while (readFile.ready())
        {
            int i = Integer.parseInt(readFile.readLine());
            if (i % 2 == 0)
                array.add(i);
        }

        readFile.close();

        for (int i = 0; i < array.size(); )
        {
            int min = array.get(0);
            int a = 0;
            for (int j = 0; j < array.size(); j++)
            {
                if (min > array.get(j))
                {
                    min = array.get(j);
                    a = j;
                }
            }
            array.remove(a);
            array2.add(min);

        }
        for(int x: array2)
            System.out.println(x);

    }
}
