package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести все строки в файл, каждую строчку с новой стороки.
*/


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        OutputStream stream = new FileOutputStream(fileName);
        while (true)
        {
            String s = reader.readLine();
            String string = s + "\r\n";
            for (int i : string.toCharArray())
            {
                stream.write(i);
            }
            if (s.equals("exit")) break;
        }
    }
}
