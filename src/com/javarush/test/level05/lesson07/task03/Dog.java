package com.javarush.test.level05.lesson07.task03;

/* Создать класс Dog
Создать класс Dog (собака) с тремя инициализаторами:
- Имя
- Имя, рост
- Имя, рост, цвет
*/

public class Dog
{
    //Напишите тут ваш код
    private String name = null;
    private int height = 0;
    private String color = null;
    public void initialize (String name){
        this.name = name;
    }
    public void initialize (String name, int height){
        this.name = name;
        this.height = height;
    }
    public void initialize (String name, int height, String color){
        this.name = name;
        this.height = height;
        this.color = color;
    }
}
