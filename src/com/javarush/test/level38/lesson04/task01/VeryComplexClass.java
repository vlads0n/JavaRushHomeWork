package com.javarush.test.level38.lesson04.task01;

/* Проверяемые исключения (checked exception)
Напиши реализацию метода veryComplexMethod().
Он должен всегда кидать какое-нибудь проверяемое исключение.
Кинуть исключение (throw) явно нельзя.
*/

import java.io.FileNotFoundException;
import java.io.FileReader;

public class VeryComplexClass {
    public void veryComplexMethod() throws FileNotFoundException {
        FileReader fileReader = new FileReader("c:\\Vlad.txt");
    }
}
