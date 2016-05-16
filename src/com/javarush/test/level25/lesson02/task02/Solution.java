package com.javarush.test.level25.lesson02.task02;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            try
            {
                if (loadWheelNamesFromDB().length != 4)
                    throw new IllegalArgumentException();

                wheels = new LinkedList<>();
                Set<String> list = new HashSet<>();
                for (Wheel i : Wheel.values())
                    list.add(i.toString());

                for (int i = 0; i < loadWheelNamesFromDB().length; i++)
                {
                    if (list.contains(loadWheelNamesFromDB()[i]))
                        wheels.add(Wheel.valueOf(loadWheelNamesFromDB()[i]));
                    else
                        throw new IllegalArgumentException();
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Это не машина");
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }
}
