package com.javarush.test.level08.lesson08.task01;

import java.util.HashSet;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static HashSet<String> createSet()
    {
        //Напишите тут ваш код
        HashSet<String> set = new HashSet<String>();
        set.add("лень");
        set.add("любовь");
        set.add("лето");
        set.add("линк");
        set.add("лакоста");
        set.add("лимфа");
        set.add("луна");
        set.add("луг");
        set.add("лектор");
        set.add("лев");
        set.add("лань");
        set.add("лава");
        set.add("лего");
        set.add("лассо");
        set.add("лига");
        set.add("лоб");
        set.add("лед");
        set.add("лом");
        set.add("лук");
        set.add("лост");
        return set;
    }
}
