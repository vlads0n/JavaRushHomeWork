package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Влад on 12.11.2015.
 */
public class Restaurant
{
    private final static int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args)
    {
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Lilla");
        StatisticEventManager.getInstance().register(cook1);
        StatisticEventManager.getInstance().register(cook2);

        Waitor waitor = new Waitor();

        cook1.addObserver(waitor);
        cook2.addObserver(waitor);

        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tabletList.add(tablet);
            tablet.addObserver(cook1);
            tablet.addObserver(cook2);
        }

        RandomOrderGeneratorTask randomOrderGeneratorTask = new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(randomOrderGeneratorTask);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
