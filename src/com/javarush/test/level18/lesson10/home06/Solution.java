package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileInputStream reader = new FileInputStream(args[0]);
        ArrayList<Character> list1 = new ArrayList<Character>();
        ArrayList<Character> list2 = new ArrayList<Character>();
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();

        char data;
        while (reader.available() > 0)
        {
            data = (char) reader.read();
            list1.add(data);
            list2.add(data);
        }

        for (int i = 0; i < list1.size(); i++)
        {
            int index = 0;
            int count = 0;
            for (int j = 0; j < list2.size(); )
            {
                if(list1.get(i).equals(list2.get(j)) && count > 0)
                {
                    count++;
                    list2.remove(j);
                }
                else if (list1.get(i).equals(list2.get(j)))
                {
                    list2.remove(0);
                    count++;
                }
                else
                    j++;

            }
            if (count > 0)
                map.put(list1.get(i), count);
        }

        Iterator <Map.Entry <Character, Integer>> iterator = map.entrySet().iterator();


        while(iterator.hasNext())
        {
            Map.Entry<Character, Integer> pair = iterator.next();
            Character key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + "  " + value);
        }

        reader.close();
    }
}
