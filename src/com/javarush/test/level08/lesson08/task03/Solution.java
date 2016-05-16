package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //Напишите тут ваш код
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("Винниченко","Владислав");
        map.put("Винник","Владимир");
        map.put("Мазур","Владимир");
        map.put("Терещенко","Юрий");
        map.put("Семенченко","Ирина");
        map.put("Сех","Николай");
        map.put("Сокол","Максим");
        map.put("Удод","Александр");
        map.put("Вовк","Евгений");
        map.put("Норрис","Чак");

        return map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //Напишите тут ваш код
        int count = 0;
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String FirstName = pair.getValue();
            if (FirstName.equals(name))
                count++;
        }
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String familiya)
    {
        //Напишите тут ваш код
        int count = 0;
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<String, String> pair = iterator.next();
            String SecondName = pair.getKey();
            if (SecondName.equals(familiya))
                count++;
        }
        return count;
    }
}
