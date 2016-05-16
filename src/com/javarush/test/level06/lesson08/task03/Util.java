package com.javarush.test.level06.lesson08.task03;

/* Класс Util
Реализовать статический метод double getDistance(x1, y1, x2, y2). Он должен вычислять расстояние между точками.
Используй метод double Math.sqrt(double a), который вычисляет квадратный корень переданного параметра
*/

public class Util
{
    public static double getDistance(int x1, int y1, int x2, int y2)
    {
        //Напишите тут ваш код
        int X = (x2-x1)*(x2-x1);
        int Y = (y2-y1)*(y2-y1);

        double result = Math.sqrt(X+Y);
        return result;
    }
}
