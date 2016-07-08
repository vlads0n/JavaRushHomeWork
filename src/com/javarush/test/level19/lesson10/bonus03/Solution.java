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
import java.util.List;

public class Solution {
    static String openTag;
    static String closingTag;
    static String string;
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        try  (FileReader fileReader = new FileReader(reader.readLine())){
            while (fileReader.ready())
                stringBuilder = stringBuilder.append((char) fileReader.read());
        }
        catch (IOException e) {}

        openTag = "<" + args[0];
        closingTag = "</" + args[0];
        string = stringBuilder.toString().replaceAll("\\r\\n", "");

        int indexOfOpenTag = string.indexOf(openTag);
        int indexOfClosingTag = string.indexOf(closingTag);
        List<String> tagList = recursive(indexOfOpenTag, indexOfClosingTag);

        for (String tag : tagList)
            System.out.println(tag);
    }
        public static List<String> recursive(int indexOfOpenTag, int indexOfClosingTag) {
            if (string.substring(indexOfOpenTag + 4, indexOfClosingTag).contains(openTag)) {
                int newIndexOfOpenTag = string.indexOf(openTag, indexOfOpenTag + 5);
                recursive(newIndexOfOpenTag, indexOfClosingTag);
                int newIndexOfClosingTag = string.indexOf(closingTag, indexOfClosingTag + 7);
                recursive(indexOfOpenTag, newIndexOfClosingTag);
            }
            else
                result.add(string.substring(indexOfOpenTag, indexOfClosingTag + 7));
        return result;
    }
}
