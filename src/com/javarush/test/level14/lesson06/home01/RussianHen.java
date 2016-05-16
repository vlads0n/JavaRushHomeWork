package com.javarush.test.level14.lesson06.home01;


public class RussianHen extends Hen
{
    int getCountOfEggsPerMonth()
    {
        return 23;
    }
    String getDescription()
    {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
