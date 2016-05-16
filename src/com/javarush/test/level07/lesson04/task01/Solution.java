package com.javarush.test.level07.lesson04.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Максимальное среди массива на 20 чисел
1. В методе initializeArray():
1.1. Создайте массив на 20 чисел
1.2. Считайте с консоли 20 чисел и заполните ими массив
2. Метод max(int[] array) должен находить максимальное число из элементов массива
*/

public class Solution
{
    private static int[] array;

    public static void main(String[] args) throws Exception
    {
        int[] array = initializeArray();
        int max = max(array);
        System.out.println(max);
    }
    public static int[] initializeArray() throws IOException {
        //initialize(create and fill) an array here - инициализируйте (создайте и заполните) массив тут
        int [] array = new int [20];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 20; i++)
        {
            int a = Integer.parseInt(reader.readLine());
            array[i] = a;
        }
        return array;
    }

    public static int max(int[] array) {
        //find the max value here - найдите максимальное значение в этом методе
        int Max = array[0];
        for (int i = 0; i < 20; i++)
        {
            if (Max < array[i])
            {
                Max = array[i];
            }
        }
        return Max;
    }
}
