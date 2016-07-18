package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Влад on 24.11.2015.
 */
public class Cook extends Observable implements Runnable
{
    private String name;
    private boolean isBusy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public void startCookingOrder(Order order) {
        isBusy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        try {
            Thread.currentThread().sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
        }
        CookedOrderEventDataRow eventDataRow = new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes());
        StatisticEventManager.getInstance().register(eventDataRow);

        setChanged();
        notifyObservers(order);
        isBusy = false;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
                try {
                    startCookingOrder(queue.take());
                }
                catch (InterruptedException e) {
                }
            }
        }
    }
}
