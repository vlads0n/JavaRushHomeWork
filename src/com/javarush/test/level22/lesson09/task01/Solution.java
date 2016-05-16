package com.javarush.test.level22.lesson09.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример, "мор"-"ром", "трос"-"сорт"
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(buffer.readLine()));
        StringBuilder stringBuilder;
        String string, buff;
        String[] stringArray;
        ArrayList<String> arrayList = new ArrayList<String>();
        while ((buff = bufferedReader.readLine()) != null)
        {
            stringArray = buff.split(" ");
            for (String i : stringArray)
                arrayList.add(i);
        }
        for (int i = 0; i < arrayList.size(); i++)
        {
            stringBuilder = new StringBuilder(arrayList.get(i)).reverse();
            string = stringBuilder.toString();
            for (int j = 0; j < arrayList.size(); j++)
            {
                if (string.equals(arrayList.get(j)) && (i != j))
                {
                    result.add(new Pair(arrayList.get(i), arrayList.get(j)));
                    arrayList.remove(j);
                }
            }
        }
        for (Pair i: result)
            System.out.println(i.toString());
        buffer.close();
        bufferedReader.close();
    }

    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second)
        {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
