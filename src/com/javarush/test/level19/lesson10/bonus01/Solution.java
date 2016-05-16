package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
[Файл 1]
строка1
строка2
строка3

[Файл 2]
строка1
строка3
строка4

[Результат - список lines]
SAME строка1
REMOVED строка2
SAME строка3
ADDED строка4
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader file2 = new BufferedReader(new FileReader(reader.readLine()));
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        while (file1.ready())
            list1.add(file1.readLine());
        while (file2.ready())
            list2.add(file2.readLine());
        int i = 0;
        int j = 0;
        while (true)
        {
            if (list1.get(i).equals(list2.get(j)))
            {
                lines.add(new LineItem(Type.SAME, list1.get(i)));
                j++;
                i++;
            } else if (j < list2.size() - 1 && list1.get(i).equals(list2.get(j + 1)))
            {
                lines.add(new LineItem(Type.ADDED, list2.get(j)));
                j++;
            } else if (i < list1.size() - 1 && list1.get(i + 1).equals(list2.get(j)))
            {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                i++;
            }
            else

                break;

            if (i == list1.size() && j < list2.size())
            {
                for (;j < list2.size(); j++)
                    lines.add(new LineItem(Type.ADDED, list2.get(j)));

            }

            if (j == list2.size() && i < list1.size())
            {
                for (; i < list1.size(); i++)
                    lines.add(new LineItem(Type.REMOVED, list1.get(i)));

            }

            if (i == list1.size() && j == list2.size())
                break;

        }
        reader.close();
        file1.close();
        file2.close();

        for (LineItem x : lines)
            System.out.println(x.type + " " + x.line);
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
