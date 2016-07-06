package com.javarush.test.level27.lesson15.big01.ad;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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

    public Set<Advertisement> getActiveAdvertisement() {
        Set<Advertisement> result = new TreeSet<>(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() > 0)
                result.add(advertisement);
        }
        return result;
    }

    public Set<Advertisement> getArchivedAdvertisement() {
        Set<Advertisement> result = new TreeSet<>(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : advertisementStorage.list()) {
            if (advertisement.getHits() == 0)
                result.add(advertisement);
        }
        return result;
    }
}
