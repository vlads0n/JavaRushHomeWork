package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

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

    public Map<Date, Double> reportAdvertisementProfit() {
        Map<Date, Double> resultMap = new TreeMap<>(Collections.reverseOrder());
        List<EventDataRow> list = statisticStorage.getList(EventType.SELECTED_VIDEOS);
        for (EventDataRow eventDataRow : list) {
            Date date = getDate(eventDataRow.getDate());
            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            if (resultMap.containsKey(date))
                resultMap.put(date, resultMap.get(date) + 0.01d * (double) videoSelectedEventDataRow.getAmount());
            else
                resultMap.put(date, 0.01d * (double) videoSelectedEventDataRow.getAmount());
        }
        return resultMap;
    }

    public Map<Date, Map<String, Double>> cookWorkDuration() {
        Map<Date, Map<String, Double>> resultMap = new TreeMap<>(Collections.reverseOrder());
        Map<String, Double> cookMap = new TreeMap<>();
        List<EventDataRow> list = statisticStorage.getList(EventType.COOKED_ORDER);
        for (EventDataRow eventDataRow : list) {
            Date date = getDate(eventDataRow.getDate());
            CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) eventDataRow;
            String cookName = cookedOrderEventDataRow.getCookName();
            if (cookMap.containsKey(cookName))
                cookMap.put(cookName, cookMap.get(cookName) + (double) cookedOrderEventDataRow.getTime());
            else
                cookMap.put(cookName, (double) cookedOrderEventDataRow.getTime());
            resultMap.put(date, cookMap);
        }
        return resultMap;
    }

    private Date getDate (Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);
        return gregorianCalendar.getTime();
    }
}
