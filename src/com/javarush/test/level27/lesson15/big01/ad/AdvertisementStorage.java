package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 28.11.2015.
 */
class AdvertisementStorage
{
    private static AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage()
    {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "second", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "третий", 400, 1, 10 * 60));
        add(new Advertisement(someContent, "fourth", 400, 1, 20 * 60));
        add(new Advertisement(someContent, "пятый", 400, 2, 40 * 60));
        add(new Advertisement(someContent, "six", 400, 2, 30 * 60));
        add(new Advertisement(someContent, "seven", 400, 2, 50 * 60));
        add(new Advertisement(someContent, "eight", 150, 2, 20 * 60));
        add(new Advertisement(someContent, "девятый", 7000, 2, 10 * 60));
    }

    public static AdvertisementStorage getInstance()
    {
        return instance;
    }

    public List<Advertisement> list()
    {
        return videos;
    }

    public void add (Advertisement advertisement)
    {
        videos.add(advertisement);
    }

}
