package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by ���� on 23.10.2015.
 */
public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String string = "";
        try
        {
            string = reader.readLine();
            if (string.trim().equalsIgnoreCase("exit"))
                throw new InterruptOperationException();
        }
        catch (IOException e)
        {}
        return string;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        String result = "";
        boolean isTrue = true;
        while (isTrue)
        {
            writeMessage(res.getString("choose.currency.code"));
            String code = readString().trim();
            if (code.length() == 3)
            {
                result = code.toUpperCase();
                isTrue = false;
            }
            else
                writeMessage(res.getString("invalid.data"));
        }
        return result;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        boolean isTrue = true;
        String[] result = new String[2];
        String string;
        while (isTrue)
        {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            string = readString().trim();
            result = string.split("\\s+");
            try
            {
                if (result.length == 2 && Integer.parseInt(result[0]) > 0 && Integer.parseInt(result[1]) > 0)
                    isTrue = false;
                else
                    writeMessage(res.getString("invalid.data"));
            }
            catch (Exception e)
            {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        Operation operation = null;
        boolean isTrue = true;
        writeMessage(res.getString("choose.operation"));
        writeMessage(String.format("%s - %d, %s - %d, %s - %d, %s - %d",
                res.getString("operation.INFO"), Operation.INFO.ordinal(),
                res.getString("operation.DEPOSIT"), Operation.DEPOSIT.ordinal(),
                res.getString("operation.WITHDRAW"), Operation.WITHDRAW.ordinal(),
                res.getString("operation.EXIT"), Operation.EXIT.ordinal()));
        while (isTrue)
        {
            int choose = Integer.parseInt(readString());
            if (choose == 1)
            {
                operation = Operation.getAllowableOperationByOrdinal(1);
                isTrue = false;
            }
            else if (choose == 2)
            {
                operation = Operation.getAllowableOperationByOrdinal(2);
                isTrue = false;
            }
            else if (choose == 3)
            {
                operation = Operation.getAllowableOperationByOrdinal(3);
                isTrue = false;
            }
            else if (choose == 4)
            {
                operation = Operation.getAllowableOperationByOrdinal(4);
                isTrue = false;
            }
            else
            {
                try
                {
                    Operation.getAllowableOperationByOrdinal(choose);
                }
                catch (IllegalArgumentException e)
                {
                    writeMessage(res.getString("invalid.data"));
                }
            }
        }
        return operation;
    }

    public static void printExitMessage()
    {
        writeMessage(res.getString("the.end"));
    }
}
