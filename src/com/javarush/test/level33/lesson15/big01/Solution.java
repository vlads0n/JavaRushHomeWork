package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Влад on 28.03.2016.
 */
public class Solution {

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String string : strings)
            ids.add(shortener.getId(string));
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long key : keys)
            strings.add(shortener.getString(key));
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++)
            strings.add(Helper.generateRandomString());
        Shortener shortener = new Shortener(strategy);

        Long t1 = new Date().getTime();
        Set<Long> ids = getIds(shortener, strings);
        Long t2 = new Date().getTime();
        Long delta = t2 - t1;
        Helper.printMessage(delta.toString());

        t1 = new Date().getTime();
        Set<String> resultStrings = getStrings(shortener, ids);
        t2 = new Date().getTime();
        delta = t2 - t1;
        Helper.printMessage(delta.toString());

        if (strings.equals(resultStrings))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }

    public static void main(String[] args) {
        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);

        StorageStrategy storageStrategy = new OurHashMapStorageStrategy();
        testStrategy(storageStrategy, 10000);

        StorageStrategy storageStrategy1 = new FileStorageStrategy();
        testStrategy(storageStrategy1, 100);

        StorageStrategy storageStrategy2 = new OurHashBiMapStorageStrategy();
        testStrategy(storageStrategy2, 10000);

        StorageStrategy storageStrategy3 = new HashBiMapStorageStrategy();
        testStrategy(storageStrategy3, 10000);

        StorageStrategy storageStrategy4 = new DualHashBidiMapStorageStrategy();
        testStrategy(storageStrategy4, 10000);
    }
}
