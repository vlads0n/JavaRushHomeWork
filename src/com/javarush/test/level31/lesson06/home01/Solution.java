package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        File file = new File(fileName);
        String zipArchive = args[1];

        HashMap<ZipEntry, ByteArrayOutputStream> map = getAllZipFiles(zipArchive);

        setFileToZip(zipArchive, file);

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipArchive));
        for (Map.Entry<ZipEntry, ByteArrayOutputStream> pair : map.entrySet())
            if (!file.getName().split("\\.")[0].equals(pair.getKey().getName().split("\\.")[0])) {
                zipOutputStream.putNextEntry(pair.getKey());
                zipOutputStream.write(pair.getValue().toByteArray());
            }
        zipOutputStream.close();


    }
    public static HashMap<ZipEntry, ByteArrayOutputStream> getAllZipFiles(String zipArchive) throws IOException {
        HashMap<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipArchive));
        ZipEntry entry;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] buff;
        int count;

        while ((entry = zipInputStream.getNextEntry()) != null) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            buff = new byte[1024];
            while ((count = zipInputStream.read(buff)) != -1)
                byteArrayOutputStream.write(buff, 0, count);
            map.put(entry, byteArrayOutputStream);
        }
        byteArrayOutputStream.close();
        zipInputStream.close();
        return map;
    }

    public static void setFileToZip(String zipArchive, File file) throws IOException {
        ZipEntry zipEntry = new ZipEntry("new" + "\\" + file.getName());

        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int count;

        while ((count = fileInputStream.read(buff)) != -1)
            byteArrayOutputStream.write(buff, 0, count);

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipArchive));
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(byteArrayOutputStream.toByteArray());

        byteArrayOutputStream.close();
        zipOutputStream.close();
    }
}
