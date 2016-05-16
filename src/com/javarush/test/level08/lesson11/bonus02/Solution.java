package com.javarush.test.level08.lesson11.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* Нужно добавить в программу новую функциональность
Задача: Программа определяет, какая семья (фамилию) живёт в доме с указанным номером.
Новая задача: Программа должна работать не с номерами домов, а с городами:
Пример ввода:
Москва
Ивановы
Киев
Петровы
Лондон
Абрамовичи

Лондон

Пример вывода:
Абрамовичи
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //list of addresses
        Map<String, String> addresses = new HashMap<String, String>();
        while (true)
        {
            String family = reader.readLine();
            if (family.isEmpty())
                break;
            else
            {
                String city = reader.readLine();
                addresses.put(family, city);
            }
        }

        //read home number
        String InterestFamily = reader.readLine();

        Iterator<Map.Entry<String, String>> iterator = addresses.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String Family = pair.getKey();
            String City = pair.getValue();
            if (Family.equals(InterestFamily))
                System.out.println(City);
        }
    }
}
