package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;

import java.util.Map;

/**
 * Created by Влад on 28.06.2016.
 */
public class DirectorTablet {
    private StatisticManager statisticManager = StatisticManager.getInstance();

    public void printAdvertisementProfit() {
        double totalProfit = 0;
        for (Map.Entry<String, Double> pair : statisticManager.reportAdvertisementProfit().entrySet()) {
            ConsoleHelper.writeMessage(String.format("%s - %.2f", pair.getKey(), pair.getValue()));
            totalProfit += pair.getValue();
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", totalProfit));
    }

    public void printCookWorkloading() {
        for (Map.Entry<String, Map<String, Double>> pair : statisticManager.cookWorkDuration().entrySet()) {
            ConsoleHelper.writeMessage(pair.getKey());
            for (Map.Entry<String, Double> cook : pair.getValue().entrySet()) {
                if (cook.getValue() != null)
                    ConsoleHelper.writeMessage(String.format("%s - %.0f min", cook.getKey(), Math.ceil(cook.getValue() / 60)));
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {}

    public void printArchivedVideoSet() {}
}
