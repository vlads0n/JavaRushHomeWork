package com.javarush.test.level27.lesson15.big01.ad;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 30.06.2016.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveAdvertisement() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() > 0)
                result.add(advertisement);
        }
        return result;
    }

    public List<Advertisement> getArchivedAdvertisement() {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() == 0)
                result.add(advertisement);
        }
        return result;
    }
}
