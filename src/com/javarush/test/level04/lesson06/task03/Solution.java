package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
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

        if (a<b && b<c)
            System.out.println(c + " " +  b + " " + a);
        else if (a<c && c<b)
            System.out.println(b + " " +  c + " " + a);
        else if (b<a && a<c)
            System.out.println(c + " " +  a + " " + b);
        else if (b<c && c<a)
            System.out.println(a + " " +  c + " " + b);
        else if (c<a && a<b)
            System.out.println(b + " " +  a + " " + c);
        else
            System.out.println(a + " " +  b + " " + c);

    }
}
