package com.javarush.test.level22.lesson05.task02;


/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException
    {
        String string1;
        String string2;
        try
        {
            string1 = string.substring(string.indexOf("\t") + 1);
            string2 = string1.substring(0, string1.indexOf("\t"));
            return string2;
        }
        catch (Exception e)
        {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }

}
