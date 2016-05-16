package com.javarush.test.level05.lesson09.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя конструкторами:
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    //Напишите тут ваш код
    private String MyName = null;
    public Friend (String name)
{
    this.MyName = name;
}
    private int age = Integer.parseInt(null);
    public Friend (String name, int age)
    {
        this.MyName = name;
        this.age = age;
    }
    private String gender = null;
    public Friend (String name, int age, String gender)
    {
        this.MyName = name;
        this.age = age;
        this.gender = gender;
    }
}