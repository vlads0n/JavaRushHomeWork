package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        String words = "";
        String[] wordsArray;

        while (fileReader.ready())
            words += (char) fileReader.read();

        wordsArray = words.split(" ");

        StringBuilder result = getLine(wordsArray);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> deleteStrings = new ArrayList<>();
        String endLetter;
        int count;

        if (words == null || words.length == 0)
            return new StringBuilder();
        if ("".equals(words[0]) || words.length == 1)
            return new StringBuilder(words[0]);

        Collections.addAll(list1, words);

        for (int i = 0; i < list1.size(); i++) {
            list2.addAll(list1);
            list2.remove(i);
            for (int j = 0; j < list2.size(); j++) {
                count = 1;
                endLetter = list1.get(i).substring(list1.get(i).length() - 1);
                result = result.append(list1.get(i));
                for (int k = 0; k < list2.size(); ) {
                    if (endLetter.equalsIgnoreCase(list2.get(k).substring(0, 1))) {
                        result = result.append(" ").append(list2.get(k));
                        endLetter = list2.get(k).substring(list2.get(k).length() - 1);
                        deleteStrings.add(list2.get(k));
                        list2.remove(k);
                        k = 0;
                        count++;
                    }
                    else
                        k++;
                }
                if (count == list1.size())
                    return result;
                else {
                    list2.addAll(deleteStrings);
                    result = result.delete(0, result.length());
                }
                deleteStrings.clear();
            }
            list2.clear();
        }
        return result;
    }
}
