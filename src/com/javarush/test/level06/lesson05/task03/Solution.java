package com.javarush.test.level06.lesson05.task03;

/* По 50 000 объектов Cat и Dog
Создать в цикле по 50 000 объектов Cat и Dog. (Java-машина должна начать уничтожать неиспользуемые, и метод finalize хоть раз да вызовется).
*/

public class Solution
{
    public static void main(String[] args)
    {
        //Напишите тут ваш код
        for (int i = 0; i < 49999; i++)
        {
            Cat cat = new Cat ("Vaska");
            Dog dog = new Dog ("Spaik");
        }
    }
}
class Cat
{
    public String name;
    public Cat(String name)
    {
        this.name = name;
    }
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        System.out.println("Cat destroyed");
    }
}

class Dog
{
    public String name;
    public Dog(String name)
    {
        this.name = name;
    }
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        System.out.println("Dog destroyed");
    }
}