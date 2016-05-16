package com.javarush.test.level08.lesson08.task05;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
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
        map.put("Семенченко","Максим");
        map.put("Сех","Николай");
        map.put("Сокол","Максим");
        map.put("Удод","Александр");
        map.put("Вовк","Евгений");
        map.put("Норрис","Чак");

        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //Напишите тут ваш код
        HashMap<String, String> map2 = new HashMap<String, String>(map);

        for (Map.Entry<String ,String> pair1 : map2.entrySet()) {
            int count = 0;
            for (Map.Entry<String, String> pair2 : map2.entrySet()) {
                if (pair1.getValue().equals(pair2.getValue())) {
                    count++;
                }
                if (count > 1) {
                    removeItemFromMapByValue(map, pair1.getValue());
                }
            }
        }

    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
