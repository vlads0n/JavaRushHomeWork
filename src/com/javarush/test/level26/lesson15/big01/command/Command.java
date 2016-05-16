package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.IOException;

/**
 * Created by ���� on 25.10.2015.
 */
interface Command
{
    void execute() throws InterruptOperationException;
}
