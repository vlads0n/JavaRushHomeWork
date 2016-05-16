package com.javarush.test.level04.lesson16.home03;

/* Посчитать сумму чисел
Вводить с клавиатуры числа и считать их сумму. Если пользователь ввел -1, вывести на экран сумму и завершить программу.  -1 должно учитываться в сумме.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        //Напишите тут ваш код
        int a = 0, s = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (a != -1){
            int b = Integer.parseInt(reader.readLine());
            s = s + b;
        if (b == -1)
            break;
        }
        System.out.println(s);


    }
}
