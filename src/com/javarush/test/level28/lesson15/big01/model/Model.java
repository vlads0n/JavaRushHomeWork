package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 18.01.2016.
 */
public class Model {

    private View view;
    private Provider[] providers;

    public Model(View view, Provider[] providers) {
        if (view != null)
            this.view = view;
        else
            throw new IllegalArgumentException();
        if (providers.length > 0)
            this.providers = providers;
        else
            throw new IllegalArgumentException();
    }

    public void selectCity(String city)
    {
        List<Vacancy> list = new ArrayList<>();
        for (Provider provider : providers)
        {
            for (Vacancy vacancy : provider.getJavaVacancies(city))
                list.add(vacancy);
        }
        view.update(list);
    }
}
