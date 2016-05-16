package com.javarush.test.level26.lesson15.big01;


import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by Влад on 23.10.2015.
 */
public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount()
    {
        int result = 0;
        for (Map.Entry<Integer, Integer> map : denominations.entrySet())
            result += map.getKey() * map.getValue();
        return result;
    }

    public boolean hasMoney()
    {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        TreeMap<Integer, Integer> result = new TreeMap<>();
        ArrayList<Integer> denomination = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet())
            denomination.add(pair.getKey());
        Collections.sort(denomination);

        int j = denomination.size() - 1;

        while (denomination.get(j) > expectedAmount)
            j--;

        int tempSum, count, totalCount;
        try
        {
            do
            {
                if ((tempSum = expectedAmount % denomination.get(j)) >= 0)
                {
                    count = expectedAmount / denomination.get(j);
                    if (count < (totalCount = denominations.get(denomination.get(j))))
                    {
                        expectedAmount = tempSum;
                        result.put(denomination.get(j), count);
                    }
                    else
                    {
                        expectedAmount = tempSum + (count - totalCount) * denomination.get(j);
                        result.put(denomination.get(j), totalCount);
                    }
                }
                while (denomination.get(j) > expectedAmount)
                {
                    j--;
                    if (j < 0)
                        break;
                }
            }
            while (0 <= j);

            if (expectedAmount > 0)
                throw new NotEnoughMoneyException();

            for (Map.Entry<Integer, Integer> pair : result.entrySet())
            {
                int difference = denominations.get(pair.getKey()) - pair.getValue();
                if (difference > 0)
                    denominations.put(pair.getKey(), difference);
                else
                    denominations.remove(pair.getKey());
            }
        }
        catch (ConcurrentModificationException e)
        {}

        return result.descendingMap();
    }
}
