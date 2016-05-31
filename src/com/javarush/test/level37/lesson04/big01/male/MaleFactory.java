package com.javarush.test.level37.lesson04.big01.male;

import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by Влад on 31.05.2016.
 */
public class MaleFactory {
    public Human getPerson(int age) {
        if (KidBoy.MAX_AGE >= age)
            return new KidBoy();
        else if (TeenBoy.MAX_AGE >= age)
            return new TeenBoy();
        else
            return new Man();
    }
}
