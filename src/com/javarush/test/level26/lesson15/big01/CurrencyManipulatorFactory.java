package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Влад on 23.10.2015.
 */
public final class CurrencyManipulatorFactory
{
    private static HashMap<String, CurrencyManipulator> currencyManipulatorHashMap = new HashMap<>();

    private CurrencyManipulatorFactory()
    {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        if (currencyManipulatorHashMap.containsKey(currencyCode))
            return currencyManipulatorHashMap.get(currencyCode);
        else
        {
            currencyManipulatorHashMap.put(currencyCode, new CurrencyManipulator(currencyCode));
            return currencyManipulatorHashMap.get(currencyCode);
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return currencyManipulatorHashMap.values();
    }
}
