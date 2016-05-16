package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
Закрыть потоки
*/

import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileReader reader = new FileReader(args[0]);
        float spaceFrequency;
        int space = 0;
        int symbol = 0;
        int data;
        while ((data = reader.read()) != -1)
        {
            if (data == 32)
                space++;
            symbol++;
        }
        spaceFrequency = (float) ((double) space / (double) symbol * 100);
        System.out.println(String.format("%.2f", spaceFrequency));
        reader.close();
    }
}
