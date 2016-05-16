package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<Human> children = new ArrayList<Human>();

        Human child1 = new Human("сын Максим", true, 12, new ArrayList<Human>());
        Human child2 = new Human("сын Олег", true, 8, new ArrayList<Human>());
        Human child3 = new Human("дочь Алена", false, 3, new ArrayList<Human>());

        children.add(child1);
        children.add(child2);
        children.add(child3);

        Human Father = new Human ("папа Олег", true, 40, children);
        Human Mother = new Human ("мама Оля", false, 35, children);

        ArrayList<Human> childrens1 = new ArrayList<Human>();
        childrens1.add(Father);

        ArrayList<Human> childrens2 = new ArrayList<Human>();
        childrens2.add(Mother);

        Human GFather1 = new Human("дедушка Вася", true, 75, childrens1);
        Human GFather2 = new Human("дедушка Ваня", true, 72, childrens2);
        Human GMother1 = new Human("бабушка Маша", false, 68, childrens1);
        Human GMother2 = new Human("бабушка Ира", false, 70, childrens2);

        System.out.println(GFather1);
        System.out.println(GMother1);
        System.out.println(GFather2);
        System.out.println(GMother2);
        System.out.println(Father);
        System.out.println(Mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);



    }

    public static class Human
    {
        public String name;
        public boolean sex;
        public int age;
        ArrayList<Human> children = new ArrayList<Human>();

        public Human (String name, boolean sex, int age, ArrayList<Human> children)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
