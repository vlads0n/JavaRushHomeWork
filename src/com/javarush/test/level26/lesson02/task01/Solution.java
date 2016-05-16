package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {

        Arrays.sort(array);

        final int median;
        int middle = array.length / 2;
        int size = array.length;

        if (size % 2 == 0)
            median = (array[middle - 1] + array[middle]) / 2;
        else
            median = array[middle];

        Comparator<Integer> comparatorByMedian = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int result1 = Math.abs(o1 - median);
                int result2 = Math.abs(o2 - median);
                int result = result1 - result2;
                return result == 0 ? (o1 - o2) : result;
            }
        };
        Arrays.sort(array, comparatorByMedian);
        return array;
    }

    public static void main(String[] args)
    {
        Integer[] array1 = new Integer[]{2, 3, 5, 6, 18, 19, 1, 5, 20, 18};
        Integer[] array2 = new Integer[]{3, 5, 6, 18, 19, 1, 5, 20, 18};

        for (Integer i : sort(array1))
            System.out.print(i + " ");

        System.out.println();

        for (Integer i : sort(array2))
            System.out.print(i + " ");
    }
}
