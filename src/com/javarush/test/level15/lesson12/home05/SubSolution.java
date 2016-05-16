package com.javarush.test.level15.lesson12.home05;

/**
 * Created by Влад on 21.01.2015.
 */
public class SubSolution extends Solution
{
    public SubSolution(Integer pub1, Integer pub4)
    {
        super(pub1, pub4);
    }

    public SubSolution(String pub2)
    {
        super(pub2);
    }

    public SubSolution(Integer pub3)
    {
        super(pub3);
    }

    SubSolution(String def1, String def4)
    {
        super(def1, def4);
    }

    SubSolution(Double def2)
    {
        super(def2);
    }

    SubSolution(String def3, Integer def5)
    {
        super(def3, def5);
    }

    protected SubSolution(Character prot1)
    {
        super(prot1);
    }

    protected SubSolution(Boolean prot2)
    {
        super(prot2);
    }

    protected SubSolution(Integer prot3, String prot4)
    {
        super(prot3, prot4);
    }

    private SubSolution (int priv1) {super(priv1);}
    private SubSolution (int priv2, int priv4) {super(priv2, priv4);}
    private SubSolution (int priv3, String priv5) {super(priv3, priv5);}
}
