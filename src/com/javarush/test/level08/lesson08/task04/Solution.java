package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Сталлоне", new Date("JUNE 1 1995"));
        map.put("Винниченко", new Date("NOVEMBER 26 1990"));
        map.put("Винник", new Date("AUGUST 26 1990"));
        map.put("Мазур", new Date("JULY 26 1990"));
        map.put("Терещенко", new Date("JUNE 26 1990"));
        map.put("Семенченко", new Date("SEPTEMBER 26 1990"));
        map.put("Сех", new Date("MAY 26 1990"));
        map.put("Сокол", new Date("JULY 26 1990"));
        map.put("Удод", new Date("MARCH 26 1990"));
        map.put("Вовк", new Date("OCTOBER 26 1990"));

        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //Напишите тут ваш код
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<String, Date> pair = iterator.next();
            if (pair.getValue().getMonth() >=5 && pair.getValue().getMonth() <= 7)
                iterator.remove();
        }
     }
}
