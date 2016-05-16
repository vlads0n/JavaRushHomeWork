package com.javarush.test.level14.lesson08.bonus01;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            int[] Array = new int[5];
            System.out.println(Array[6]);
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            Object[] Array = new String[5];
            Array[0] = new Character('*');
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            Object ch = new Character('*');
            System.out.print((Byte)ch);
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            int[] Array = new int[-5];
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            int[] Array = new int[5];
            Array = null;
            int a = Array.length;
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            String a = "Vlad";
            char ch = a.charAt(10);
        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            InputStream is = new FileInputStream("111.txt");
        }
        catch(Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            Integer.parseInt("none");

        }
        catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new UnsupportedOperationException("Invalid operation for sorted list.");
        }
        catch(Exception e)
        {
            exceptions.add(e);
        }

    }
}
