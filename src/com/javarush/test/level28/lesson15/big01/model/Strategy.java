package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.List;

/**
 * Created by Влад on 30.12.2015.
 */
public interface Strategy
{
    List<Vacancy> getVacancies(String searchString);
}
