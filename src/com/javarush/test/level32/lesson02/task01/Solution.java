package com.javarush.test.level32.lesson02.task01;

import java.io.IOException;
import java.io.RandomAccessFile;

/* Запись в файл
В метод main приходят три параметра:
1) fileName - путь к файлу
2) number - число, позиция в файле
3) text - текст
Записать text в файл fileName начиная с позиции number.
Если файл слишком короткий, то записать в конец файла.
*/
public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");

        long sizeFile = randomAccessFile.length();
        long numberPosition = Long.parseLong(args[1]);

        if (sizeFile < numberPosition) {
            randomAccessFile.seek(sizeFile);
            for (int i = 2; i < args.length; i++)
                randomAccessFile.writeBytes(args[i]);
        }
        else {
            randomAccessFile.seek(numberPosition);
            for (int i = 2; i < args.length; i++)
                randomAccessFile.writeBytes(args[i]);
        }
        randomAccessFile.close();
    }
}
