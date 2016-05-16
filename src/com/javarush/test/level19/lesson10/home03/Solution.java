package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready())
        {
            String name = "";
            String[] array = reader.readLine().split(" ");
            String date = array[array.length - 3] + " " + array[array.length - 2] + " " + array[array.length - 1];
            Date birthday = new SimpleDateFormat("dd MM yyyy").parse(date);
            for (int i = 0; i < array.length - 3; i++)
            {
                name += array[i] + " ";
            }
            PEOPLE.add(new Person(name.substring(0, name.length() - 1), birthday));
        }
        reader.close();
    }

}
