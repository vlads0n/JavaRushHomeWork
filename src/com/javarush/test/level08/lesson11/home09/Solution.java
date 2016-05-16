package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

/* public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(isDateOdd("JANUARY 1 2000"));
        System.out.println(isDateOdd("JANUARY 2 2020"));
    }

    public static boolean isDateOdd(String date)
    {
        Date currentTime = new Date(date);

        Date YearStartTime = new Date();

        YearStartTime.setYear(currentTime.getYear());
        YearStartTime.setMonth(0);
        YearStartTime.setDate(1);
        YearStartTime.setHours(0);
        YearStartTime.setMinutes(0);
        YearStartTime.setSeconds(0);

        long msTimeDistance = 0;
        msTimeDistance = currentTime.getTime() - YearStartTime.getTime();
        long msDay = 1000 * 60 * 60 * 24;
        int DayCount = (int)(msTimeDistance / msDay);

        if (DayCount % 2 == 0)
            return true;
        else
            return false;
    }
} */

public class Solution {
    public static void main(String[] args) {
        System.out.println(isDateOdd("MARCH 1 1960"));
        System.out.println(isDateOdd("MARCH 1 1995"));
        System.out.println(isDateOdd("MARCH 1 2000"));
        System.out.println(isDateOdd("MARCH 1 2001"));
        System.out.println(isDateOdd("MARCH 1 2002"));
        System.out.println(isDateOdd("MARCH 1 2003"));
        System.out.println(isDateOdd("MARCH 1 2004"));
        System.out.println(isDateOdd("MARCH 1 2020"));
    }

    public static boolean isDateOdd(String date)
    {
        Date currentDate = new Date(date); //Получение даты



        Date startTime = new Date(0); // Начало года
        startTime.setSeconds(0);
        startTime.setMinutes(0);
        startTime.setHours(0);
        startTime.setMonth(0);
        startTime.setDate(1);
        startTime.setYear(currentDate.getYear());



        long msTimeDistance = 0;

        if (currentDate.getMonth() > 2 && currentDate.getMonth() < 10)
        { msTimeDistance = currentDate.getTime() - startTime.getTime() + 3600000;
        } // Перевод часов на летнее время
        else
        { msTimeDistance = currentDate.getTime() - startTime.getTime();
        } // Зимнее время

        long msDay = 24 * 60 * 60 * 1000;
        int dayCount = (int) (msTimeDistance / msDay + 1); // +1, чтобы посчитался первый день



        return dayCount % 2 == 0? false: true;
    }
}