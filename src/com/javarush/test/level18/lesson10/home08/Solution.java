package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.*;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Не забудьте закрыть все потоки
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String k;
        while (!(k = reader.readLine()).equals("exit"))
        {
            new ReadThread(k).start();
        }


        reader.close();

    }

    public static class ReadThread extends Thread {

        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        public void run()
        {
            try
            {
                readFile(this.fileName);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void readFile (String fileName) throws IOException
        {
            FileInputStream inputStream = new FileInputStream(fileName);

            Map<Integer, Integer> collection = new HashMap<Integer, Integer>();
            ArrayList<Integer> collect = new ArrayList<Integer>();
            ArrayList<Integer> rescollect = new ArrayList<Integer>();

            int count;
            int data;

            while(inputStream.available() > 0)
            {
                data = inputStream.read();
                collect.add(data);
                rescollect.add(data);
            }

            int max = 0;
            for (int i = 0; i < collect.size(); i++)
            {
                count = 0;
                for (int j = 0; j < rescollect.size(); )
                {
                    if (collect.get(i).equals(rescollect.get(j)))
                    {
                        count++;
                        rescollect.remove(j);
                    }
                    else
                        j++;
                }
                if (count > 0)
                {
                    collection.put(collect.get(i), count);
                    if (max < count)
                        max = count;
                }
                collect.remove(i);
            }

            for (Map.Entry<Integer, Integer> pair : collection.entrySet())
            {
                int value = pair.getValue();
                if (max == value)
                {
                    resultMap.put(fileName, pair.getKey());
                }
            }
            inputStream.close();
        }

    }
}
