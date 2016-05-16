package com.javarush.test.level05.lesson12.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maximum = 0;
        int N = Integer.parseInt(reader.readLine());

        //напишите здесь ваш код
        if (N>0){
            for (int i = 1; i < (N + 1) ; i++)
            {
                int n = Integer.parseInt(reader.readLine());
                if (i == 1)
                    maximum = n;
                else if (maximum < n)
                        maximum = n;
            }
            }
        System.out.println(maximum);
    }
}
