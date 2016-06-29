package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Влад on 28.06.2016.
 */
public class DirectorTablet {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdvertisementProfit() {
        double totalProfit = 0;
        for (Map.Entry<Date, Double> pair : StatisticManager.getInstance().reportAdvertisementProfit().entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", simpleDateFormat.format(pair.getKey()), pair.getValue()));
            totalProfit += pair.getValue();
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalProfit));
    }

    public void printCookWorkloading() {
        for (Map.Entry<Date, Map<String, Double>> pair : StatisticManager.getInstance().cookWorkDuration().entrySet()) {
            ConsoleHelper.writeMessage(simpleDateFormat.format(pair.getKey()));
            for (Map.Entry<String, Double> cook : pair.getValue().entrySet()) {
                if (cook.getValue() != null)
                    ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.0f min", cook.getKey(), Math.ceil(cook.getValue() / 60)));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {}

    public void printArchivedVideoSet() {}
}
