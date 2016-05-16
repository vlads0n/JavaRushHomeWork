package com.javarush.test.level08.lesson11.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/* Номер месяца
Программа вводит с клавиатуры имя месяца и выводит его номер на экран в виде: «May is 5 month».
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //add your code here - напиши код тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
            map.put("January", 1);
            map.put("February", 2);
            map.put("March", 3);
            map.put("April", 4);
            map.put("May", 5);
            map.put("June", 6);
            map.put("July", 7);
            map.put("August", 8);
            map.put("September", 9);
            map.put("October", 10);
            map.put("November", 11);
            map.put("December", 12);

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<String, Integer> pair = iterator.next();
            if (pair.getKey().equals(month))
                System.out.println( month + " is " + pair.getValue() + " month");
        }
    }

}
