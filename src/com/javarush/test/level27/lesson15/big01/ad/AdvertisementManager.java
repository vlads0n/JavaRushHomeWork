package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

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
        List<Advertisement> list = recursive(storage.list(), storage.list().size());

        if (list.isEmpty())
            throw new NoVideoAvailableException();

        Comparator<Advertisement> comparator = new Comparator<Advertisement>()
        {
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
        };

        Collections.sort(list, comparator);

        for (Advertisement advertisement : list)
        {
            long price = advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration();
            if (timeSeconds >= advertisement.getDuration())
            {
                ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... " + advertisement.getAmountPerOneDisplaying() + ", " + price);
                advertisement.revalidate();
                timeSeconds = timeSeconds - advertisement.getDuration();
            }
        }
    }

    private List<Advertisement> recursive(List<Advertisement> list, int time)
    {
        ArrayList<Advertisement> tempList = new ArrayList<>();
        for (Advertisement advertisement : list)
        {
            if (advertisement.getDuration() <= time)
                tempList.add(advertisement);
        }
        return tempList;
    }
}
