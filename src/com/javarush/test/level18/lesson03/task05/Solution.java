package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        reader.close();

        ArrayList<Integer> collect1 = new ArrayList<Integer>();
        ArrayList<Integer> collect2 = new ArrayList<Integer>();
        ArrayList<Integer> collect3 = new ArrayList<Integer>();

        int data;
        while(inputStream.available()>0) // заповнюємо масив байтами із файлу
        {
            data = inputStream.read();
            collect1.add(data);
        }

        int count;
        for (int i = 0; i < collect1.size(); )
        {
            count = 0;
            for (int j = 0; j < collect1.size(); )
            {
                if (collect1.get(i).equals(collect1.get(j)) && count == 0)
                {
                    collect2.add(collect1.get(i));
                    count++;
                    j++;
                }
                else if (collect1.get(i).equals(collect1.get(j)) && count != 0)
                {
                    collect1.remove(j);
                }
                else
                    j++;
            }
            collect1.remove(i);
        }

        for (int i = 0; i < collect2.size(); i++)
            collect1.add(collect2.get(i));


        int min;
        int a;
        for (int i = 0; i < collect1.size(); i++)
        {
            min = collect2.get(0);
            a = 0;
            for (int j = 0; j < collect2.size(); j++)
            {
                if (min >= collect2.get(j))
                {
                    min = collect2.get(j);
                    a = j;
                }
            }
            collect2.remove(a);
            collect3.add(i, min);
        }


        for (Integer i: collect3)
            System.out.print (i + " ");


        inputStream.close();
    }
}
