package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/


import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));

        if (args[0].equals("-c") && args.length >= 4) {
            printWriter.println(getId(fileName) + getProductName(args) + getPrice(args) + getQuantity(args));
        }

        printWriter.close();
    }

    private static String getProductName(String[] args) {
        String result = "";

        for (int i = 1; i < args.length - 2; i++)
            result += args[i] + " ";

        while (result.length() < 30)
            result += " ";

        if (result.length() > 30)
            return result.substring(0, 30);
        else
            return result;
    }

    private static String getPrice(String[] args) {
        String result = args[args.length - 2];

        while (result.length() < 8)
            result += " ";

        if (result.length() > 8)
            return result.substring(0, 8);
        else
            return result;
    }

    private static String getQuantity(String[] args) {
        String result = args[args.length - 1];

        while (result.length() < 4)
            result += " ";

        if (result.length() > 4)
            return result.substring(0, 4);
        else
            return result;
    }

    private static String getId(String fileName) throws IOException {
        String result ;
        ArrayList<Long> listId = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String string;
        Long id;
        Long maxId = 0L;

        while ((string = reader.readLine()) != null) {
            id = Long.parseLong(string.substring(0, 8).trim());
            listId.add(id);
        }

        reader.close();

        for (Long i : listId) {
            if (i > maxId)
                maxId = i;
        }

        maxId++;
        result = maxId + "";

        while (result.length() < 8)
            result += " ";

        if (result.length() > 8)
            return result.substring(0, 8);
        else
            return result;
    }
}
