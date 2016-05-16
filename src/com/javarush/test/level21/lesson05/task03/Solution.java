package com.javarush.test.level21.lesson05.task03;


import java.util.Date;

/* Ошибка в equals/hashCode
Исправьте ошибки реализаций методов equals и hashCode для класса Solution
*/
public class Solution {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private Solution solution;

    public Solution(int anInt, String string, double aDouble, Date date, Solution solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (o.getClass() != getClass())
            return false;

        Solution solution1 = (Solution) o;

        return (solution1.anInt == this.anInt) && (solution1.string == this.string) && (solution1.aDouble == this.aDouble) && (solution1.date == this.date) && (solution1.solution == this.solution);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + anInt;
        result = result * prime + (string == null ? 0 : string.hashCode());
        result = result * prime + ((Double) aDouble).hashCode();
        result = result * prime + (date == null ? 0 : date.hashCode());
        result = result * prime + (solution == null ? 0 : solution.hashCode());
        return result;
    }
}
