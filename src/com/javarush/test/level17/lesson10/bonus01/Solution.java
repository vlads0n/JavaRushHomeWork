package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution
{
    public static List<Person> allPeople = new ArrayList<>();


    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }


    public static void main(String[] args) throws ParseException
    {
        String pol = "";
        String name = "";
        int id;
        if (args.length > 0)
        {
            if ("-c".equals(args[0]) && args.length >= 4)
            {
                create(args);
            } else if ("-u".equals(args[0]) && args.length >= 5)
            {
                update(args);
            } else if ("-d".equals(args[0]) && args.length == 2)
            {
                delete(args);
            } else if ("-i".equals(args[0]) && args.length == 2)
            {
                info(args);
            }
        }
    }

    static void create(String... args) throws ParseException
    {
        SimpleDateFormat sfIn = new SimpleDateFormat("d/M/y", Locale.ENGLISH);
        String name = "";
        for (int i = 1; i < args.length - 2; i++)
        {
            name += " " + args[i];
        }
        name = name.substring(1);

        if (args[args.length - 2].equals("м"))
        {
            allPeople.add(Person.createMale(name, sfIn.parse(args[args.length - 1])));

        } else if (args[args.length - 2].equals("ж"))
        {
            allPeople.add(Person.createFemale(name, sfIn.parse(args[args.length - 1])));

        } else
        {
            return;
        }
        System.out.println(allPeople.size() - 1);
    }

    static void update(String... args) throws ParseException
    {
        SimpleDateFormat sfIn = new SimpleDateFormat("d/M/y", Locale.ENGLISH);
        String name = "";
        int id;
        for (int i = 2; i < args.length - 2; i++)
        {
            name += " " + args[i];
        }

        name = name.substring(1);
        id = Integer.parseInt(args[1]);
        if (id < allPeople.size())
        {
            if (args[args.length - 2].equals("м"))
            {
                allPeople.get(id).setName(name);
                allPeople.get(id).setSex(Sex.MALE);
                allPeople.get(id).setBirthDay(sfIn.parse(args[args.length - 1]));
            } else if (args[args.length - 2].equals("ж"))
            {
                allPeople.get(id).setName(name);
                allPeople.get(id).setSex(Sex.FEMALE);
                allPeople.get(id).setBirthDay(sfIn.parse(args[args.length - 1]));
            }
        }
    }

    static void delete(String... args)
    {
        int id;
        id = Integer.parseInt(args[1]);
        if (id < allPeople.size())
        {
            allPeople.get(id).setName(null);
            allPeople.get(id).setSex(null);
            allPeople.get(id).setBirthDay(new Date(Long.MIN_VALUE));
        }
    }

    static void info(String... args)
    {
        String pol = "";
        int id;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        id = Integer.parseInt(args[1]);
        if (id < allPeople.size())
        {
            if (allPeople.get(id).getSex() == Sex.MALE) pol = "м";
            else if (allPeople.get(id).getSex() == Sex.FEMALE) pol = "ж";
            else pol = "null";

            String date;

            if (sf.format(allPeople.get(id).getBirthDay()).equals("02-Dec-292269055"))
            {
                date = null;
            } else date = sf.format(allPeople.get(id).getBirthDay());


            System.out.println(allPeople.get(id).getName() + " " +
                    pol + " " +
                    date);
        }
    }
}
