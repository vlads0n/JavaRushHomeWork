package com.javarush.test.level30.lesson02.task01;

/* Осваиваем методы класса Integer
Используя метод Integer.parseInt(String, int) реализуйте логику метода convertToDecimalSystem,
который должен переводить переданную строку в десятичное число и возвращать его в виде строки.
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int tempNumber;
        int resultNumber = 0;
        String result = "";
        int count = 1;
        if (s.charAt(0) == '0')
        {
            if (s.charAt(1) == 'x') {
                for (int i = s.length() - 1; i >= 2; i--) {
                    String tempSymbol = s.charAt(i) + "";
                    if (tempSymbol.equalsIgnoreCase("A"))
                        tempNumber = 10;
                    else if (tempSymbol.equalsIgnoreCase("B"))
                        tempNumber = 11;
                    else if (tempSymbol.equalsIgnoreCase("C"))
                        tempNumber = 12;
                    else if (tempSymbol.equalsIgnoreCase("D"))
                        tempNumber = 13;
                    else if (tempSymbol.equalsIgnoreCase("E"))
                        tempNumber = 14;
                    else if (tempSymbol.equalsIgnoreCase("F"))
                        tempNumber = 15;
                    else
                        tempNumber = Integer.parseInt(s.charAt(i) + "");
                    tempNumber = tempNumber * count;
                    count *= 16;
                    resultNumber += tempNumber;
                }
                return result + resultNumber;
            }
            else if (s.charAt(1) == 'b') {
                for (int i = s.length() - 1; i >= 2; i--) {
                    tempNumber = Integer.parseInt(s.charAt(i) + "");
                    tempNumber = tempNumber * count;
                    count *= 2;
                    resultNumber += tempNumber;
                }
                return result + resultNumber;
            }
            else {
                for (int i = s.length() - 1; i >= 1; i--) {
                    tempNumber = Integer.parseInt(s.charAt(i) + "");
                    tempNumber = tempNumber * count;
                    count *= 8;
                    resultNumber += tempNumber;
                }
                return result + resultNumber;
            }
        }
        else
            return s;
    }
}
