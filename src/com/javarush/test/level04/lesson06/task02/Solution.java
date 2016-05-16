package com.javarush.test.level04.lesson06.task02;

/* Максимум четырех чисел
Ввести с клавиатуры четыре числа, и вывести максимальное из них.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String chislo1 = reader.readLine();
        int a = Integer.parseInt(chislo1);

        String chislo2 = reader.readLine();
        int b = Integer.parseInt(chislo2);

        String chislo3 = reader.readLine();
        int c = Integer.parseInt(chislo3);

        String chislo4 = reader.readLine();
        int d = Integer.parseInt(chislo4);

        if (a > b && a > c && a > d)
            System.out.println(a);
        else if (b > a && b > c && b > d)
            System.out.println(b);
        else if (c > a && c > b && c > d)
            System.out.println(c);
        else
            System.out.println(d);
    }
}
