package com.javarush.test.level09.lesson02.task02;

/* И снова StackTrace
Написать пять методов, которые вызывают друг друга. Каждый метод должен возвращать имя метода, вызвавшего его, полученное с помощью StackTrace.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        method1();
    }

    public static String method1()
    {
        method2();
        StackTraceElement[] stackTraceElements1 = Thread.currentThread().getStackTrace();
        return stackTraceElements1[2].getMethodName();

    }

    public static String method2()
    {
        method3();
        StackTraceElement[] stackTraceElements2= Thread.currentThread().getStackTrace();
        return stackTraceElements2[2].getMethodName();

    }

    public static String method3()
    {
        method4();
        StackTraceElement[] stackTraceElements3 = Thread.currentThread().getStackTrace();
        return stackTraceElements3[2].getMethodName();

    }

    public static String method4()
    {
        method5();
        StackTraceElement[] stackTraceElements3 = Thread.currentThread().getStackTrace();
        return stackTraceElements3[2].getMethodName();

    }

    public static String method5()
    {
        StackTraceElement[] stackTraceElements4 = Thread.currentThread().getStackTrace();
        return stackTraceElements4[2].getMethodName();

    }
}
