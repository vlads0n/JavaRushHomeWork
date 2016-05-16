package com.javarush.test.level22.lesson05.task01;

import java.util.ArrayList;


/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
*/
public class Solution {
    public static String getPartOfString(String string)
    {

        ArrayList<Integer> index = new ArrayList<Integer>();
        int count = 0;
        if (string == null)
            throw new TooShortStringException();
        else if (string.length() < 5)
            throw new TooShortStringException();

        for (int i = 0; i < string.length(); i++)
        {
            if (string.charAt(i) == ' ')
            {
                index.add(i);
                count++;
            }
        }

        if (count < 5)
            throw new TooShortStringException();
        else
        {
            return string.substring(index.get(0) + 1, index.get(4));
        }
    }

    public static class TooShortStringException extends RuntimeException
    {
    }



}
