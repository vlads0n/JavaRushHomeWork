package com.javarush.test.level20.lesson10.bonus01;

import java.util.*;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int[] result;
        ArrayList<Integer> resultList = new ArrayList<>();
        int S, M, tmp, tmpNumber, s;

        for (int i = 1; i < N; i++) {
            s = i;
            M = (int) Math.log10(s) + 1;
            S = 0;
            while (s > 0) {
                tmpNumber = 1;
                if ((tmp = s % 10) > 1) {
                    for (int j = 0; j < M; j++) {
                        tmpNumber *= tmp;
                    }
                }
                else if (tmp == 0)
                    tmpNumber = 0;
                S += tmpNumber;
                s /= 10;
            }
            if (S == i)
                resultList.add(S);
        }

        result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++)
            result[i] = resultList.get(i);

        return result;
    }


    public static void main(String[] args) {
        Long t0 = System.currentTimeMillis();
        int n = 21474672;
        int[] numbers = getNumbers(n);
        Long t1 = System.currentTimeMillis();
        System.out.println("time: " + (t1 - t0) / 1000d + " sec");
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");
        for (int number : numbers) {
            System.out.print(number + ", ");
        }
    }
}
