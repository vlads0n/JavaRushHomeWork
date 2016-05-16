package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть поток ввода.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(reader.readLine()));
        int count = 0;
        while (file.ready())
        {
            String s = file.readLine().replaceAll("[\\p{Punct}]", " ");
            String[] a = s.split(" ");
            for (int i = 0; i < a.length; i++)
            {
                if (a[i].equals("world"))
                    count++;
            }
        }
        System.out.println(count);
        file.close();
        reader.close();
    }
}
