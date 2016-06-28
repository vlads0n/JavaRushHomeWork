package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Vinnik on 22.06.2016.
 */
public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> map = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType : EventType.values())
                map.put(eventType, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data) {
            map.get(data.getType()).add(data);
        }

        private List<EventDataRow> getList(EventType eventType) {
            return map.get(eventType);
        }
    }

    public Map<String, Double> reportAdvertisementProfit() {
        Map<String, Double> resultMap = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> list = statisticStorage.getList(EventType.SELECTED_VIDEOS);
        for (EventDataRow eventDataRow : list) {
            String date = getDate(eventDataRow.getDate());
            if (resultMap.get(date) != null)
                resultMap.put(date, resultMap.get(date) + 0.01d * ((VideoSelectedEventDataRow) eventDataRow).getAmount());
            else
                resultMap.put(date, 0.01d * ((VideoSelectedEventDataRow) eventDataRow).getAmount());
        }
        return resultMap;
    }

    public Map<String, Map<String, Double>> cookWorkDuration() {
        Map<String, Map<String, Double>> resultMap = new TreeMap<>(Collections.reverseOrder());
        Map<String, Double> cookMap = new TreeMap<>();
        List<EventDataRow> list = statisticStorage.getList(EventType.COOKED_ORDER);
        for (EventDataRow eventDataRow : list) {
            String date = getDate(eventDataRow.getDate());
            String cookName = ((CookedOrderEventDataRow) eventDataRow).getCookName();
            if (cookMap.get(cookName) != null)
                cookMap.put(cookName, cookMap.get(cookName) + eventDataRow.getTime());
            else
                cookMap.put(cookName, (double) eventDataRow.getTime());
            resultMap.put(date, cookMap);
        }
        return resultMap;
    }

    private String getDate (Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }
}
