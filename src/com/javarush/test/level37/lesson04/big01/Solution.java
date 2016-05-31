package com.javarush.test.level37.lesson04.big01;

import com.javarush.test.level37.lesson04.big01.male.MaleFactory;

/**
 * Created by Влад on 31.05.2016.
 */
public class Solution {
    public static void main(String[] args) {
        MaleFactory maleFactory = new MaleFactory();
        System.out.println(maleFactory.getPerson(99).toString());
        System.out.println(maleFactory.getPerson(4).toString());
        System.out.println(maleFactory.getPerson(15).toString());
    }
}
