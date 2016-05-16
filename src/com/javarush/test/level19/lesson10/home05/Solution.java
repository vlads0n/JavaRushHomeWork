package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        FileWriter fileWriter = new FileWriter(args[1]);
        while (reader.ready())
        {
            String[] array = reader.readLine().split(" ");
            for (String i: array)
            {
                if (i.contains("0") || i.contains("1") || i.contains("2") || i.contains("3") || i.contains("4") || i.contains("5") || i.contains("6") || i.contains("7") || i.contains("8") || i.contains("9"))
                    fileWriter.write(i + " ");
            }
        }
        reader.close();
        fileWriter.close();
    }
}
