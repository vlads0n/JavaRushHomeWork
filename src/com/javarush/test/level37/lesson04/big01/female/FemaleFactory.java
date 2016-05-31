package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by Влад on 31.05.2016.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        if (KidGirl.MAX_AGE >= age)
            return new KidGirl();
        else if (TeenGirl.MAX_AGE >= age)
            return new TeenGirl();
        else
            return new Woman();
    }
}
