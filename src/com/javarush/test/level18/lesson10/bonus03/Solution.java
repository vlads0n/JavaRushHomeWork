package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        if (args[0].equals("-u") && args.length >= 5) {
            update(fileName, args[1], args);
        }
        else if (args[0].equals("-d") && args.length == 2) {
            delete(fileName, args[1]);
        }

    }

    private static void delete(String fileName, String id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String string;
        String result = "";
        int tmpId;
        int currentId;
        while ((string = reader.readLine()) != null) {
            tmpId = Integer.parseInt(string.substring(0, 8).trim());
            currentId = Integer.parseInt(id);
            if (tmpId != currentId)
                result += string + "\n";
        }
        reader.close();
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(result);

        fileWriter.close();
    }

    private static void update(String fileName, String id, String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String string;
        String result = "";
        int tmpId;
        int currentId;
        String stringId;
        while ((string = reader.readLine()) != null) {
            tmpId = Integer.parseInt(string.substring(0, 8).trim());
            currentId = Integer.parseInt(id);
            if (tmpId != currentId)
                result += string + "\n";
            else {
                stringId = currentId + "";
                while (stringId.length() < 8)
                    stringId += " ";
                if (stringId.length() > 8)
                    stringId = id.substring(0, 8);

                result += stringId + getProductName(args) + getPrice(args) + getQuantity(args) + "\n";
            }
        }
        reader.close();
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(result);

        fileWriter.close();
    }

    private static String getProductName(String[] args) {
        String result = "";

        for (int i = 2; i < args.length - 2; i++)
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
}
