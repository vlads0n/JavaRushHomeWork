package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Vinnik on 18.07.2016.
 */
public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    @Override
    public void update(Observable o, Object arg) {
        try {
            queue.put((Order) arg);
        } catch (InterruptedException e) {
        }
    }
}
