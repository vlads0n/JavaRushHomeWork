package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байты, которые встречаются в файле меньше всего раз.
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        reader.close();
        HashMap<Integer, Integer> collection = new HashMap<Integer, Integer>();
        ArrayList<Integer> collect = new ArrayList<Integer>();
        ArrayList<Integer> rescollect = new ArrayList<Integer>();

        int count;
        int data;

        while(inputStream.available()>0) // заповнюємо два масива байтами із файлу
        {
            data = inputStream.read();
            collect.add(data);
            rescollect.add(data);
        }

        int min = 999;
        for (int i = 0; i < collect.size(); i++)
        {
            count = 0;
            for (int j = 0; j < rescollect.size(); )
            {
                if (collect.get(i).equals(rescollect.get(j)))    // знаходимо однакові байти
                {
                    count++;                                    // додаємо лічильник
                    rescollect.remove(j);                       // видаляємо із другого масиву цей байт
                }
                else
                    j++;
            }
            if (count > 0)
            {
                collection.put(collect.get(i), count);          // заповнюємо корлекцію цими байтами (ключ - "байт", значення - "к-сть повторень")
                if (min > count)                                // знаходимо мінімальну к-сть повторень
                    min = count;
            }
            collect.remove(i);
        }


        Iterator<Map.Entry<Integer, Integer>> iterator = collection.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> pair = iterator.next(); // берем пару
            int value = pair.getValue();                        // записуємо значення у змінну
            if (min == value)                                   // якщо значення дорівнює мінімальному, то виводимо на екран байт
            {
                System.out.print (pair.getKey() + " ");
            }
        }
        inputStream.close();
    }
}
