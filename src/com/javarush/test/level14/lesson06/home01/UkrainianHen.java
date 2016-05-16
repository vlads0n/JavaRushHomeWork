package com.javarush.test.level14.lesson06.home01;


public class UkrainianHen extends Hen
{
    int getCountOfEggsPerMonth()
    {
        return 33;
    }
    String getDescription()
    {
        return super.getDescription() + " Моя страна - " + Country.UKRAINE + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}