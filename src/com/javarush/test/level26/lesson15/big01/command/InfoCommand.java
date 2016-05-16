package com.javarush.test.level26.lesson15.big01.command;


import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.ResourceBundle;


/**
 * Created by ���� on 25.10.2015.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    public InfoCommand()
    {
    }

    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().size() != 0)
        {
            int count = 0;
            for (CurrencyManipulator pair : CurrencyManipulatorFactory.getAllCurrencyManipulators())
            {
                if (pair.hasMoney())
                {
                    ConsoleHelper.writeMessage(pair.getCurrencyCode() + " - " + pair.getTotalAmount());
                    count++;
                }
            }
            if (count == 0)
                ConsoleHelper.writeMessage(res.getString("no.money"));

        }
        else
        {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
