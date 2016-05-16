package com.javarush.test.level05.lesson07.task01;

/* Создать класс Friend
Создать класс Friend (друг) с тремя инициализаторами (тремя методами initialize):
- Имя
- Имя, возраст
- Имя, возраст, пол
*/

public class Friend
{
    //Напишите тут ваш код
    private String MyName = null;
    public void initialize (String name)
    {
        this.MyName = name;
    }
    private int age = Integer.parseInt(null);
    public void initialize (String name, int age)
    {
        this.MyName = name;
        this.age = age;
    }
    private String gender = null;
    public void initialize (String name, int age, String gender)
    {
        this.MyName = name;
        this.age = age;
        this.gender = gender;
    }
}
