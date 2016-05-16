package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] array = reader.readLine().toCharArray();
        String part = "";
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == '?')
            {
                for (int j = i + 1; j < array.length; j++)
                {
                    part += array[j];
                }
            }
        }
        String[] part2 = part.split("&");
        for (int i = 0; i < part2.length; i++)
        {
            String[] part3 = part2[i].split("=");
            System.out.print(part3[0] + " ");
        }
        System.out.println();
        for (int i = 0; i < part2.length; i++)
        {
            String[] part3 = part2[i].split("=");
            if (part3[0].equals("obj"))
            {
                try
                {
                    double a = Double.parseDouble(part3[1]);
                    alert(a);
                }
                catch (Exception e)
                {
                    alert(part3[1]);
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
