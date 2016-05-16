package com.javarush.test.level08.lesson11.home08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Пять наибольших чисел
Создать массив на 20 чисел. Заполнить его числами с клавиатуры. Вывести пять наибольших чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

        System.out.println(array[0]);
        System.out.println(array[1]);
        System.out.println(array[2]);
        System.out.println(array[3]);
        System.out.println(array[4]);
    }

    public static void sort(int[] array)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> max = new ArrayList<Integer>();

        for (int i = 0; i < 20; i++)
            list.add(array[i]);


        for (int i = 0; i < 5; i++)
        {
            int maxCount = list.get(0);
            int a = 0;
            for (int j = 0; j < list.size(); j++)
            {
                if (maxCount < list.get(j))
                {
                    maxCount = list.get(j);
                    a = j;
                }
            }
            max.add(maxCount);
            list.remove(a);
        }

        for (int i = 0; i < max.size(); i++)
            array[i] = max.get(i);
    }
}
