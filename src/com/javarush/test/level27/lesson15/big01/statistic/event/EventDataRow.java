package com.javarush.test.level27.lesson15.big01.statistic.event;

import java.util.Date;

/**
 * Created by Vinnik on 22.06.2016.
 */
public interface EventDataRow {

    EventType getType();
    Date getDate();
    int getTime();
}