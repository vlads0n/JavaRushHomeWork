package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by ���� on 25.10.2015.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    public WithdrawCommand()
    {
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        boolean isTrue = true;
        while (isTrue)
        {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String amount = ConsoleHelper.readString();
            if (amount.matches("^[1-9]{1}[0-9]*$"))
            {
                int expectedAmount = Integer.parseInt(amount);
                try
                {
                    if (currencyManipulator.isAmountAvailable(expectedAmount))
                    {
                        for (Map.Entry<Integer, Integer> pair : currencyManipulator.withdrawAmount(expectedAmount).entrySet())
                            ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), expectedAmount, currencyCode));
                        isTrue = false;
                    }
                    else
                    {
                        ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    }
                }
                catch (NotEnoughMoneyException e)
                {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                }
            }
            else
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
        }

    }
}
