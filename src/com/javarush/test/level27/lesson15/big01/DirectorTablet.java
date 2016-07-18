package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Влад on 28.06.2016.
 */
public class DirectorTablet {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit() {
        double totalProfit = 0;
        for (Map.Entry<Date, Double> pair : StatisticEventManager.getInstance().reportAdvertisementProfit().entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", simpleDateFormat.format(pair.getKey()), pair.getValue()));
            totalProfit += pair.getValue();
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalProfit));
    }

    public void printCookWorkloading() {
        for (Map.Entry<Date, Map<String, Integer>> pair : StatisticEventManager.getInstance().cookWorkDuration().entrySet()) {
            ConsoleHelper.writeMessage(simpleDateFormat.format(pair.getKey()));
            for (Map.Entry<String, Integer> cook : pair.getValue().entrySet()) {
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d min", cook.getKey(), cook.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getActiveAdvertisement();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(String.format("%s - %d",
                    advertisement.getName(),
                    advertisement.getHits()));
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getArchivedAdvertisement();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}
