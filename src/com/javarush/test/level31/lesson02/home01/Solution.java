package com.javarush.test.level31.lesson02.home01;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        
        List<File> allFiles = new ArrayList<>();
        getFiles(path, allFiles);

        List<File> saveFiles = new ArrayList<>();

        for (File file : allFiles) {
            if (!file.getAbsolutePath().equals(resultFileAbsolutePath.getAbsolutePath())) {
                if (file.length() > 50)
                    file.delete();
                else {
                    saveFiles.add(file);
                }
            }
        }
        Collections.sort(saveFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        File renameFile = new File (resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        resultFileAbsolutePath.renameTo(renameFile);
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(renameFile);

        for (File file : saveFiles)
        {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileOutputStream.write(buffer);
            fileOutputStream.write("\r".getBytes());
            fileOutputStream.write("\n".getBytes());
            fileInputStream.close();
        }
        fileOutputStream.close();
    }

    public static void getFiles(File directory, List<File> list)
    {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                if (file.list().length == 0)
                    file.delete();
                else
                    getFiles(file, list);
            } else {
                list.add(file);
            }
        }
    }
}
