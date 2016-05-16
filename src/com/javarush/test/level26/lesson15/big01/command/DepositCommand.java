package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;


/**
 * Created by ���� on 25.10.2015.
 */
class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    public DepositCommand()
    {
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] count = ConsoleHelper.getValidTwoDigits(currencyCode);

        int denomination = Integer.parseInt(count[0]);
        int number = Integer.parseInt(count[1]);

        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).addAmount(denomination, number);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).getTotalAmount(), currencyCode));
    }
}
