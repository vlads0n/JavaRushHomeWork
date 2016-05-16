package com.javarush.test.level33.lesson15.big01.strategies;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

/**
 * Created by Влад on 28.03.2016.
 */
public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap<Long, String> data = new DualHashBidiMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        if (data.containsValue(value))
            return data.getKey(value);
        else
            return null;
    }

    @Override
    public String getValue(Long key) {
        if (data.containsKey(key))
            return data.get(key);
        else
            return null;
    }
}
