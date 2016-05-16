package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Влад on 22.07.2015.
 */
public class Hippodrome
{
    private static ArrayList<Horse> horses = new ArrayList<Horse>();
    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public static void main(String[] args) throws InterruptedException
    {
        game = new Hippodrome();

        Horse horse1 = new Horse ("Mira", 3, 0);
        Horse horse2 = new Horse ("Lira", 3, 0);
        Horse horse3 = new Horse ("Kiren", 3, 0);

        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        game.run();
        game.printWinner();
    }

    public void run() throws InterruptedException
    {
        for (int i = 1; i <= 100; i++)
        {
            move();
            print();
            Thread.sleep(500);
        }
    }

    public void move()
    {
        for (Horse i : horses)
            i.move();
    }

    public void print()
    {
        for (Horse i : horses)
            i.print();
        System.out.println();
        System.out.println();
    }

    public Horse getWinner()
    {
        double max = 0;
        double distance;
        Horse winnerHorse = null;
        for (Horse i : horses)
        {
            distance = i.getDistance();
            if (distance > max)
            {
                max = distance;
                winnerHorse = i;
            }
        }
        return winnerHorse;
    }

    public void printWinner()
    {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
