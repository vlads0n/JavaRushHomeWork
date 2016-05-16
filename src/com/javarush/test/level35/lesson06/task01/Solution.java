package com.javarush.test.level35.lesson06.task01;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* Простой generics
Параметризируйте класс Solution, чтобы он мог работать со всеми классами, которые наследуются от HashMap.
Метод getMap должен возвращать тип поля map.
*/
public class Solution {
    private HashMap map;

    public Solution(HashMap map) {
        this.map = map;
    }

    public <T extends HashMap> T getMap() {
        return (T) map;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("string", 4);
        Solution solution = new Solution(hashMap);
        HashMap mapFromSolution = solution.getMap();
        System.out.println(mapFromSolution.getClass());


        LinkedHashMap<Solution, Solution> hashMap2 = new LinkedHashMap<>();
        hashMap2.put(solution, solution);
        Solution solution2 = new Solution(hashMap2);
        LinkedHashMap mapFromSolution2 = solution2.getMap();   //need to cast  :(
        System.out.println(mapFromSolution2.getClass());
    }
}
