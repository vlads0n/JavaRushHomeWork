package com.javarush.test.level20.lesson10.home03;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
*/
public class Solution implements Serializable
{

    private static final long serialVersionUID = 4526987319806948106L;

    public static class A {
        protected String name = "A";

        protected A()
        {}

        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {

        private static final long serialVersionUID = -4089954864349966930L;

        public B(String name) {
            super(name);
            this.name += name;
        }
        private void writeObject(ObjectOutputStream out) throws IOException
        {
            out.writeObject(name);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            name = (String) in.readObject();
        }

    }

}
