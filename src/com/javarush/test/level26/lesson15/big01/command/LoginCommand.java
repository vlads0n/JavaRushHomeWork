package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by ���� on 02.11.2015.
 */
class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    public LoginCommand()
    {
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        boolean isTrue = true;
        String enterNumberCard;
        String enterPin;
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (isTrue)
        {
            if ((enterNumberCard = ConsoleHelper.readString()).trim().matches("\\d{12}"))
            {
                if ((enterPin = ConsoleHelper.readString()).trim().matches("\\d{4}"))
                    if (validCreditCards.containsKey(enterNumberCard) && enterPin.equals(validCreditCards.getString(enterNumberCard)))
                    {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), enterNumberCard));
                        isTrue = false;
                    }
                    else
                    {
                        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), enterNumberCard));
                        ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    }
                else
                {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            }
            else
            {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
