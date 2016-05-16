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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(reader.readLine());
        FileWriter fileWriter = new FileWriter(file, true);
        if (args.length >= 4) {
            if (args[0].equals("-c")) {
                String id = (int) (Math.random() * 100000000) + "";
                while (id.length() < 8)
                    id += " ";

                String productName = "";
                for (int i = 1; i < args.length - 2; i++)
                    productName += args[i] + " ";
                if (productName.length() > 30)
                    productName = productName.substring(0, 30);
                else {
                    while (productName.length() < 30)
                        productName += " ";
                }

                String price = args[args.length - 2];
                while (price.length() < 8)
                    price += " ";

                String quantity = args[args.length - 1];
                while (quantity.length() < 4)
                    quantity += " ";

                if (file.length() != 0) {
                    fileWriter.write("\r\n");
                    fileWriter.write(id + productName + price + quantity);
                } else
                    fileWriter.write(id + productName + price + quantity);
            }
        }
        reader.close();
        fileWriter.close();
    }
}
