package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.HashBiMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Влад on 28.03.2016.
 */
public class SpeedTest {

    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Long t1 = new Date().getTime();
        for (String string : strings)
            ids.add(shortener.getId(string));
        Long t2 = new Date().getTime();
        return t2 - t1;
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Long t1 = new Date().getTime();
        for (Long id : ids)
            strings.add(shortener.getString(id));
        Long t2 = new Date().getTime();
        return t2 - t1;
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        Set<Long> origId1 = new HashSet<>();
        Set<Long> origId2 = new HashSet<>();
        Set<String> strings1 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();

        for (int i = 0; i < 10000; i++)
            origStrings.add(Helper.generateRandomString());

        Long time1 = getTimeForGettingIds(shortener1, origStrings, origId1);
        Long time2 = getTimeForGettingIds(shortener2, origStrings, origId2);

        Assert.assertTrue(time1 > time2);

        time1 = getTimeForGettingStrings(shortener1, origId1, strings1);
        time2 = getTimeForGettingStrings(shortener2, origId2, strings2);

        Assert.assertEquals(time1, time2, 70);
    }
}
