package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        //Напишите тут ваш код
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++)
        {
            int a = Integer.parseInt(reader.readLine());
            list1.add(a);
            list2.add(a);
        }
        int min = 0;
        int a = 0;
        for (int i = 0; i < 5; i++)
        {
            min = list1.get(0);
            a = 0;
            for (int j = 0; j < list1.size(); j++)
            {
                if (min > list1.get(j))
                {
                    min = list1.get(j);
                    a = j;
                }
            }
            list1.remove(a);
            list2.set(i, min);

        }
        for (int i = 0; i < list2.size(); i++)
            System.out.println(list2.get(i));
    }
}
