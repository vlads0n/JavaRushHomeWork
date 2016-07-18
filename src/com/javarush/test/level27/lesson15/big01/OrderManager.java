package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Vinnik on 18.07.2016.
 */
public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!queue.isEmpty()) {
                        for (Cook cook : StatisticEventManager.getInstance().getCooks()) {
                            if (!cook.isBusy()) {
                                try {
                                    cook.startCookingOrder(queue.take());
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    }
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            queue.put((Order) arg);
        } catch (InterruptedException e) {
        }
    }
}
