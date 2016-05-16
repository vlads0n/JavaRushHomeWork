package com.javarush.test.level30.lesson02.home01;

import java.math.BigInteger;

/* Конвертер систем счислений
Реализуйте логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit()
из одной системы счисления(numerationSystem) в другую (expectedNumerationSystem)
бросьте NumberFormatException, если переданное число некорректно, например, число "120" с системой счисления 2
Валидация для - number.getDigit() - целое не отрицательное
Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumerationSystemType._2, "110");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._10);
        System.out.println(result);    //expected 6

        number = new Number(NumerationSystemType._2, "110100100000000110000100111011011111110011001010100010011101101101100100101100110001100110000011110");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._10);
        System.out.println(result);    //expected 519949509846173492335554907166

        number = new Number(NumerationSystemType._10, "519949509846173492335554907166");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);     //expected 110100100000000110000100111011011111110011001010100010011101101101100100101100110001100110000011110

        number = new Number(NumerationSystemType._16, "4AH");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._10);
        System.out.println(result);
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        int numerationSystem = expectedNumerationSystem.getNumerationSystemIntValue();
        if (!number.getDigit().contains("-")) {
            if (number.getNumerationSystem().equals(NumerationSystemType._10))
                return new Number(expectedNumerationSystem, fromTenSystemToOtherSystem(number.getDigit(), numerationSystem));
            else if (expectedNumerationSystem.getNumerationSystemIntValue() == 10)
                return new Number(expectedNumerationSystem, fromOtherSystemToTenSystem(number));
            else
                return new Number(expectedNumerationSystem, fromOtherSystemToOtherSystem(number, numerationSystem));
        }
        else {
            System.out.println("Digit is not valid!!!");
            return null;
        }
    }

    public static String fromNumberToLetter(int number){
        switch (number)
        {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                return number + "";
        }
    }

    public static int fromLetterToNumber(char letter){
        switch (letter) {
            case 'A':
                return  10;
            case 'B':
                return  11;
            case 'C':
                return  12;
            case 'D':
                return  13;
            case 'E':
                return  14;
            case 'F':
                return  15;
            default:
                try {
                    return Integer.parseInt(letter + "");
                }
                catch (NumberFormatException e) {
                    throw new NumberFormatException("Letter is not valid!!!");
                }
        }
    }

    public static String fromTenSystemToOtherSystem(String digitInString, int numerationSystem){
        StringBuilder resultDigit = new StringBuilder("");
        BigInteger tempDigit;
        BigInteger digit = new BigInteger(digitInString);
        while (!digit.equals(BigInteger.ZERO))
        {
            tempDigit = digit.remainder(BigInteger.valueOf(numerationSystem));
            resultDigit.append(fromNumberToLetter(tempDigit.intValue()));
            digit = digit.divide(BigInteger.valueOf(numerationSystem));
        }
        return resultDigit.reverse().toString();
    }

    public static String fromOtherSystemToTenSystem(Number number){
        BigInteger count = BigInteger.ONE;
        int tempDigit;
        BigInteger digit = BigInteger.ZERO;
        for (int i = number.getDigit().length() - 1; i >= 0 ; i--) {
            tempDigit = fromLetterToNumber(number.getDigit().toUpperCase().charAt(i));
            if (tempDigit >= number.getNumerationSystem().getNumerationSystemIntValue())
                throw new NumberFormatException("Digit is not correctly!!!");
            digit = digit.add(BigInteger.valueOf(tempDigit).multiply(count));
            count = count.multiply(BigInteger.valueOf(number.getNumerationSystem().getNumerationSystemIntValue()));
        }
        return digit + "";
    }

    public static String fromOtherSystemToOtherSystem(Number number, int numerationSystem){
        String inTenSystem = fromOtherSystemToTenSystem(number);
        return fromTenSystemToOtherSystem(inTenSystem, numerationSystem);
    }
}
