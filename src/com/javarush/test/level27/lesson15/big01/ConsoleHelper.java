package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 17.11.2015.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> result = new ArrayList<>();
        String dish;
        boolean flag;
        writeMessage("Choose the dish:");
        writeMessage(Dish.allDishesToString());
        while (true)
        {
            flag = true;
            dish = readString();

            if (dish.trim().equalsIgnoreCase("exit"))
                break;

            for (Dish i : Dish.values())
            {
                if (dish.equals(i.toString()))
                {
                    result.add(i);
                    flag = false;
                }
            }

            if (flag)
                writeMessage(dish + " is not detected");
        }

        return result;
    }
}
