package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    private static String name = "";
    private static Sex sex;
    private static SimpleDateFormat sdf = new SimpleDateFormat("d/M/y", Locale.ENGLISH);
    private static SimpleDateFormat sdfInfo = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    private static Date date;
    private static Person person;
    private static int id;
    private static int count = 0;
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if (args.length > 0) {
            switch (args[0]) {
                case "-c":
                    if (args.length >= 4)
                        create(args);
                    break;
                case "-u":
                    if (args.length >= 5)
                        update(args);
                    break;
                case "-d":
                    if (args.length >= 2)
                        delete(args);
                    break;
                case "-i":
                    if (args.length >= 2)
                        info(args);
            }
        }
    }

    public synchronized static void create(String[] args) throws ParseException {
        for (int i = 1; i < args.length; i++) {
            if (!args[i].equals("м") && !args[i].equals("ж") && count == 0) {
                name += " " + args[i];
            }
            else if (args[i].equals("м") && count == 0) {
                sex = Sex.MALE;
                count++;
            }
            else if (args[i].equals("ж") && count == 0) {
                sex = Sex.FEMALE;
                count++;
            }
            else if (count == 1) {
                date = sdf.parse(args[i]);
                count++;
            }
            if (count == 2) {
                if (sex == Sex.MALE)
                    person = Person.createMale(name.substring(1), date);
                else
                    person = Person.createFemale(name.substring(1), date);
                allPeople.add(person);
                name = "";
                count = 0;
                System.out.println(allPeople.size() - 1);
            }
        }
    }

    public synchronized static void update(String[] args) throws ParseException {
        for (int i = 1; i < args.length; i++) {
            if (count == 0) {
                id = Integer.parseInt(args[i]);
                count++;
            }
            else if (!args[i].equals("м") && !args[i].equals("ж") && count == 1) {
                name += " " + args[i];
            }
            else if (args[i].equals("м") && count == 1) {
                sex = Sex.MALE;
                count++;
            }
            else if (args[i].equals("ж") && count == 1) {
                sex = Sex.FEMALE;
                count++;
            }
            else if (count == 2) {
                date = sdf.parse(args[i]);
                count++;
            }
            if (count == 3) {
                person = allPeople.get(id);
                person.setName(name.substring(1));
                person.setBirthDay(date);
                person.setSex(sex);
                name = "";
                count = 0;
            }
        }
    }

    public synchronized static void delete(String[] args) {
        for (int i = 1; i < args.length; i++) {
            id = Integer.parseInt(args[i]);
            person = allPeople.get(id);
            person.setName(null);
            person.setSex(null);
            person.setBirthDay(new Date(Long.MIN_VALUE));
        }
    }

    public synchronized static void info(String[] args) throws ParseException {
        for (int i = 1; i < args.length; i++) {
            id = Integer.parseInt(args[i]);
            person = allPeople.get(id);
            String sexInfo;
            if (person.getSex() == Sex.MALE)
                sexInfo = "м";
            else
                sexInfo = "ж";
            System.out.println(person.getName() + " " + sexInfo + " " + sdfInfo.format(person.getBirthDay()));
        }
    }
}
