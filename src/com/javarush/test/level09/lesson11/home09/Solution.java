package com.javarush.test.level09.lesson11.home09;

import java.util.*;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        HashMap<String, Cat> map = new HashMap<String, Cat>();
        map.put("Murzic", new Cat("Murzic"));
        map.put("Mas", new Cat("Mas"));
        map.put("Ket", new Cat("Ket"));
        map.put("Nik", new Cat("Nik"));
        map.put("Koly", new Cat("Koly"));
        map.put("Volly", new Cat("Volly"));
        map.put("Molly", new Cat("Molly"));
        map.put("Dolly", new Cat("Dolly"));
        map.put("Masik", new Cat("Masik"));
        map.put("Losik", new Cat("Losik"));

        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        HashSet<Cat> set = new HashSet<Cat>();
        for(Map.Entry<String,Cat> pair:map.entrySet()){
            Cat value = pair.getValue();
            set.add(value);
        }
        return set;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
