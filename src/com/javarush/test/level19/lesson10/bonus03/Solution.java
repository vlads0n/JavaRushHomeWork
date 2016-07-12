package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        try  (FileReader fileReader = new FileReader(reader.readLine())){
            while (fileReader.ready())
                stringBuilder = stringBuilder.append((char) fileReader.read());
            reader.close();
        }
        catch (IOException e) {}

        String openTag = "<" + args[0];
        String closingTag = "</" + args[0];
        int tagLength = args[0].length();
        String string = stringBuilder.toString().replaceAll("\r\n", "");
        ArrayList<Integer> openTagList = new ArrayList<>();
        ArrayList<Integer> closingTagList = new ArrayList<>();
        int count = 0;
        int indexOfOpenTag;
        int indexOfClosingTag = 0;

        while (indexOfClosingTag != -1) {
            indexOfOpenTag = string.indexOf(openTag, count);
            indexOfClosingTag = string.indexOf(closingTag, count);
            if (indexOfOpenTag < indexOfClosingTag && indexOfOpenTag != -1)
            {
                openTagList.add(indexOfOpenTag);
                closingTagList.add(null);
                count = indexOfOpenTag + tagLength + 2;
            }
            else
            {
                for (int i = closingTagList.size() - 1; i >= 0; i--) {
                    if (closingTagList.get(i) == null) {
                        closingTagList.set(i, indexOfClosingTag);
                        break;
                    }
                }
                count = indexOfClosingTag + tagLength + 3;
            }
        }

        for (int i = 0; i < openTagList.size(); i++) {
            char isLetter = string.charAt(openTagList.get(i) + tagLength + 1);
            if (isLetter == '>' || isLetter == ' ')
                System.out.println(string.substring(openTagList.get(i), closingTagList.get(i) + tagLength + 3));
            else
                System.out.println(string.substring(openTagList.get(i), openTagList.get(i) + tagLength + 1) +
                        " " + string.substring(openTagList.get(i) + tagLength + 1, closingTagList.get(i) + tagLength + 3));
        }
    }
}
