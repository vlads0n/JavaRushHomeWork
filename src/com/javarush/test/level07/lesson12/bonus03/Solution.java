package com.javarush.test.level07.lesson12.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 20 чисел и выводит их в убывающем порядке.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[20];
        for (int i = 0; i < 20; i++)
        {
            array[i] = Integer.parseInt(reader.readLine());
        }

        sort(array);

        for (int x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(int[] array)
    {
        //Напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        int max = 0;

        for (int i = 0; i < 20; i++)
            list.add(array[i]);


        for (int i = 0; i < 20; i++)
        {
            max = list.get(0);
            int a = 0;
            for (int j = 0; j < list.size(); j++)
            {
                if (max < list.get(j))
                {
                    max = list.get(j);
                    a = j;
                }

            }
            list.remove(a);
            list2.add(max);
        }

        for (int i = 0; i < 20; i++)
            array[i] = list2.get(i);

    }
}
