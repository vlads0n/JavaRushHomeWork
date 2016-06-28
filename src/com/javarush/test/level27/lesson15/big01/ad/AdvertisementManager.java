package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Влад on 28.11.2015.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos()
    {
        List<Advertisement> advertisements = new ArrayList<>();

        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() > 0)
                advertisements.add(advertisement);
        }

        if (advertisements.isEmpty())
            throw new NoVideoAvailableException();

        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                int difference = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());

                if (difference == 0)
                {
                    long o1Price = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                    long o2Price = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();
                    return Long.compare(o1Price, o2Price);
                }
                else
                    return difference;
            }
        });

        advertisements = recursive(advertisements);

        if (advertisements.isEmpty())
            throw new NoVideoAvailableException();

        int totalAmount = 0;
        int totalDuration = 0;
        for (Advertisement advertisement : advertisements) {
            totalAmount += advertisement.getAmountPerOneDisplaying();
            totalDuration += advertisement.getDuration();
        }

        VideoSelectedEventDataRow videoSelectedEventDataRow = new VideoSelectedEventDataRow(advertisements, totalAmount, totalDuration);
        StatisticManager.getInstance().register(videoSelectedEventDataRow);

        for (Advertisement advertisement : advertisements)
        {
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    advertisement.getName(),
                    advertisement.getAmountPerOneDisplaying(),
                    advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration()));
            advertisement.revalidate();
        }
    }

    private List<Advertisement> recursive(List<Advertisement> advertisements)
    {
        int time = 0;
        for (Advertisement advertisement : advertisements) {
            time += advertisement.getDuration();
        }

        if (time > timeSeconds) {
            List<Advertisement> tempList = new ArrayList<>();
            time = 0;
            for (Advertisement advertisement : advertisements) {
                time += advertisement.getDuration();
                if (time <= timeSeconds)
                    tempList.add(advertisement);
            }

            for (int i = 0; i < advertisements.size(); i++) {
                List<Advertisement> list = new ArrayList<>(advertisements);
                list.remove(i);
                int time2 = 0;

                for (Advertisement advertisement2 : list)
                    time2 += advertisement2.getDuration();

                if (time2 > timeSeconds)
                    list = recursive(list);

                if (tempList.size() > 0)
                    compareAdvertisements(tempList, list);
                else
                    tempList.addAll(list);
            }
            return tempList;
        }
        else
            return advertisements;
    }

    private void compareAdvertisements(List<Advertisement> advertisements, List<Advertisement> list) {
        long sum1 = 0;
        long sum2 = 0;
        int time1 = 0;
        int time2 = 0;
        int hits1 = 0;
        int hits2 = 0;

        for (Advertisement advertisement : advertisements) {
            sum1 += advertisement.getAmountPerOneDisplaying();
            time1 += advertisement.getDuration();
            hits1++;
        }

        for (Advertisement advertisement : list) {
            sum2 += advertisement.getAmountPerOneDisplaying();
            time2 += advertisement.getDuration();
            hits2++;
        }

        if (sum1 < sum2) {
            advertisements.clear();
            advertisements.addAll(list);
        }
        else if (sum1 == sum2) {
            if (time1 < time2) {
                advertisements.clear();
                advertisements.addAll(list);
            }
            else if (time1 == time2) {
                if (hits1 > hits2) {
                    advertisements.clear();
                    advertisements.addAll(list);
                }
            }
        }
    }
}
