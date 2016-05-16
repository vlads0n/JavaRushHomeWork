package com.javarush.test.level16.lesson10.task02;

/* Отсчет на гонках
1. Разберись, что делает программа.
2. Реализуй логику метода run так, чтобы каждую секунду через пробел
выдавался отсчет начиная с countSeconds до 1, а потом слово [Марш!] (см примеры).
3. Если нить работает 3.5 секунды и более, прерви ее методом interrupt и внутри нити выведи в консоль слово [Прервано!].
Пример для countSeconds=4 : [4 3 2 1 Прервано!]
4. Если нить работает менее 3.5 секунд, она должна завершиться сама.
Пример для countSeconds=3 : [3 2 1 Марш!]
PS: метод sleep выбрасывает InterruptedException.
*/

import javax.swing.table.TableRowSorter;

public class Solution {
    public static volatile int countSeconds = 3;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        Thread.sleep(4000);
        clock.interrupt();
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {

            while (!isInterrupted())
                {
                    try
                    {
                        if (countSeconds == 0)
                        {
                            System.out.print ("Марш!");
                            return;
                        }
                        System.out.print (countSeconds + " ");
                        countSeconds--;
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.print ("Прервано!");
                        return;
                    }
                }
            }
        }
    }
