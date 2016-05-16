package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {

        if (args.length != 3)
            return;

        if (args[0].equals("-e"))
        {
            FileInputStream fileName = new FileInputStream(args[1]);
            FileOutputStream fileOutputStream = new FileOutputStream(args[2]);

            while (fileName.available() > 0)
            {
                int data = fileName.read() + 1;
                fileOutputStream.write(data);
            }
            fileName.close();
            fileOutputStream.close();
        }

        if (args[0].equals("-d"))
        {
            FileInputStream fileName = new FileInputStream(args[1]);
            FileOutputStream fileOutputStream = new FileOutputStream(args[2]);

            while (fileName.available() > 0)
            {
                int data = fileName.read() - 1;
                fileOutputStream.write(data);
            }
            fileName.close();
            fileOutputStream.close();
        }
    }

}
