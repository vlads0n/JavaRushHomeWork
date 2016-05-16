package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        File directory = new File(root);
        List<String> nameFiles = new ArrayList<>();
        Stack<File> stack = new Stack();
        stack.add(directory);
        while (!stack.isEmpty()) {
            directory = stack.pop();
            for (File file : directory.listFiles()) {
                if (file.isFile())
                    nameFiles.add(file.getAbsolutePath());
                else
                    stack.push(file);
            }
        }
        return nameFiles;
    }

    public static void main(String[] args) throws IOException {
        for (String string : getFileTree("C:\\Users\\Влад"))
            System.out.println(string);
    }
}
