package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> map = new TreeMap<String, Double>();
        while (reader.ready())
        {
            String[] array = reader.readLine().split(" ");
            String key = array[0];
            double value = Double.parseDouble(array[1]);
            if (map.containsKey(key))
                map.put(key, value + map.get(key));
            else
                map.put(key, value);
        }
        double maxValue = 0;
        String name;
        for (Map.Entry<String, Double> pair : map.entrySet())
        {
            if (pair.getValue() > maxValue)
                maxValue = pair.getValue();
        }
        for (Map.Entry<String, Double> pair : map.entrySet())
        {
            if (pair.getValue() == maxValue)
                System.out.println(pair.getKey());
        }
        reader.close();
    }
}
