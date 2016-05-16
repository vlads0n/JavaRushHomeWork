package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        Map<Integer, String> map = new HashMap<Integer, String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(reader.readLine()));

        String s = "";
        String[] result = null;

        while ((s = file.readLine()) != null)
        {
            result = s.split(" ");
            Integer id = Integer.parseInt(result[0]);
            map.put (id, s);
        }

        for (Map.Entry<Integer,String> pair : map.entrySet()){
            Integer choice = Integer.parseInt(args[0]);     //Указанный параметр
            Integer index = pair.getKey();

            if (choice.equals(index)) {
                System.out.println(pair.getValue());
            }
        }
        reader.close();
        file.close();
    }
}
