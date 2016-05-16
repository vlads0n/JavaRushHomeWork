package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap()
    {

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            InputStream inputStream = new FileInputStream(reader.readLine());
            load(inputStream);
        }
        catch (Exception e)
        {

        }


    }

    public void save(OutputStream outputStream) throws Exception {
        Properties properties1 = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet())
        {
            properties1.setProperty(entry.getKey(), entry.getValue());
        }
        properties1.store(outputStream, null);
        outputStream.close();
    }

    public void load(InputStream inputStream) throws Exception {
        Properties properties1 = new Properties();
        properties1.load(inputStream);
        Enumeration<?> enumeration = properties1.propertyNames();
        while (enumeration.hasMoreElements())
        {
            String key = (String) enumeration.nextElement();
            String value = properties1.getProperty(key);
            properties.put(key, value);
        }
        inputStream.close();
    }
}
