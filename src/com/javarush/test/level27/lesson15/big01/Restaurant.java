package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;


/**
 * Created by Влад on 12.11.2015.
 */
public class Restaurant
{
    public static void main(String[] args)
    {
        Tablet tablet = new Tablet(5);
        Tablet tablet1 = new Tablet(6);
        Tablet tablet2 = new Tablet(7);

        Cook cook = new Cook("Amigo");
        Cook cook1 = new Cook("Vlad");
        Cook cook2 = new Cook("Alli");

        tablet1.addObserver(cook1);
        tablet.addObserver(cook);
        tablet2.addObserver(cook2);

        Waitor waitor = new Waitor();

        cook1.addObserver(waitor);
        cook.addObserver(waitor);
        cook2.addObserver(waitor);

        tablet1.createOrder();
        tablet.createOrder();
        tablet2.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
