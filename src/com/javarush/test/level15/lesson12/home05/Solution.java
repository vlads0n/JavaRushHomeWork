package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {
    public Solution (Integer pub1, Integer pub4) {}
    public Solution (String pub2) {}
    public Solution (Integer pub3) {}

    Solution (String def1, String def4) {}
    Solution (Double def2) {}
    Solution (String def3, Integer def5) {}

    protected Solution (Character prot1) {}
    protected Solution (Boolean prot2) {}
    protected Solution (Integer prot3, String prot4) {}

    private Solution (int priv1) {}
    private Solution (int priv2, int priv4) {}
    private Solution (int priv4, String priv5) {}



}

