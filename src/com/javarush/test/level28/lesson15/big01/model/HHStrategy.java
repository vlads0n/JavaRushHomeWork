package com.javarush.test.level28.lesson15.big01.model;


import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 30.12.2015.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancyList = new ArrayList<>();
        Document document = null;
        int page = 0;
        Vacancy vacancy;
        while (true)
        {
            try {
                document = getDocument(searchString, page++);
            } catch (IOException e) {
            }
            if (document == null)
                break;
            Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (!elements.isEmpty())
            {
                for (Element element : elements)
                {
                    vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-title").text());
                    if (element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text() != null)
                        vacancy.setSalary(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                    else
                        vacancy.setSalary("");
                    vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setSiteName("hh.ua");
                    vacancy.setUrl(element.getElementsByAttributeValue("data-qa","vacancy-serp__vacancy-title").attr("href"));
                    vacancyList.add(vacancy);
                }
            }
            else
                break;
        }
        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        Document document = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36")
                .referrer("none")
                .get();
        return document;
    }
}
