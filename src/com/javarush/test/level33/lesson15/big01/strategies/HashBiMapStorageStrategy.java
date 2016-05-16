package com.javarush.test.level33.lesson15.big01.strategies;

import com.google.common.collect.HashBiMap;

/**
 * Created by Влад on 28.03.2016.
 */
public class HashBiMapStorageStrategy implements StorageStrategy {
    private HashBiMap<Long, String> data = HashBiMap.create();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.inverse().containsKey(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        if (data.inverse().containsKey(value))
            return data.inverse().get(value);
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
