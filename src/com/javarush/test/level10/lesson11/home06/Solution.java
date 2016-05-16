package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        String name;
        int age;
        boolean sex;
        String address;
        int telephone;
        String work;

        public Human (String name){
            this.name = name;
        }

        public Human (String name, int age){
            this.name = name;
            this.age = age;
        }

        public Human (String name, int age, boolean sex){
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public Human (String name, int age, boolean sex, String address){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.address = address;
        }

        public Human (String name, int age, boolean sex, String address, int telephone){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.address = address;
            this.telephone = telephone;
        }

        public Human (String name, int age, boolean sex, String address, int telephone, String work){
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.address = address;
            this.telephone = telephone;
            this.work = work;
        }

        public Human (String name, boolean sex){
            this.name = name;
            this.sex = sex;
        }

        public Human (String name, String address){
            this.name = name;
            this.address = address;
        }

        public Human (String name, int age, int telephone){
            this.name = name;
            this.age = age;
            this.telephone = telephone;
        }

        public Human (String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }
}
