package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String a =  null;
        boolean sum = false;
        int s = 0;
        while (!sum)
        {
            a = reader.readLine();
            sum = a.equals("сумма");
            if (sum)
                break;
            else
            {
                int b = 0;
                b = Integer.parseInt(a);
                s = s + b;
            }
        }
        System.out.println(s);
    }
}
