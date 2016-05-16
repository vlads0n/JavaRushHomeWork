package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter fileWriter = new FileWriter(args[1]);
        int count = 0;
        while (reader.ready())
        {
            String[] array = reader.readLine().split(" ");
            for (String i: array)
            {
                char[] chars = i.toCharArray();
                if (chars.length > 6)
                {
                    if (count == 0)
                    {
                        fileWriter.write(i);
                        count++;
                    }
                    else
                        fileWriter.write("," + i);
                }
            }
        }
        reader.close();
        fileWriter.close();
    }
}
