package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by Влад on 12.11.2015.
 */
public class Order
{
    private List<Dish> dishes;
    private Tablet tablet;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString()
    {
        if (isEmpty())
            return "";
        else
            return "Your order: " + dishes + " of Tablet{number=" + tablet.number + "}";
    }

    public boolean isEmpty()
    {
        return dishes.isEmpty();
    }

    public int getTotalCookingTime()
    {
        int result = 0;
        for (Dish i : dishes)
            result = result + i.getDuration();
        return result;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
