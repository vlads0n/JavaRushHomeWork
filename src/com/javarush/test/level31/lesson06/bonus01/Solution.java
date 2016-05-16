package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        File resultFileName = new File(args[0]);

        ArrayList<File> listFiles = new ArrayList<>();
        byte[] buff;
        int count;

        for (int i = 1; i < args.length; i++)
            listFiles.add(new File(args[i]));

        Collections.sort(listFiles);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for (File file : listFiles)
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            buff = new byte[fileInputStream.available()];
            while (fileInputStream.read(buff) > -1)
            {
                byteArrayOutputStream.write(buff);
            }
            fileInputStream.close();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);
        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null)
        {
            buff = new byte[zipInputStream.available()];
            while (zipInputStream.read(buff) > -1)
            {
                fileOutputStream.write(buff);
            }
            zipEntry = zipInputStream.getNextEntry();
        }
        fileOutputStream.close();
        zipInputStream.close();
    }
}
