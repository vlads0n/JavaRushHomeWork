package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Влад on 25.11.2015.
 */
public class Waitor implements Observer
{
    @Override
    public void update(Observable observable, Object arg)
    {
        ConsoleHelper.writeMessage(arg + " was cooked by " + observable);
    }
}
