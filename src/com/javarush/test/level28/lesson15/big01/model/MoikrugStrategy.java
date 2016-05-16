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
 * Created by Влад on 19.01.2016.
 */
public class MoikrugStrategy implements Strategy {

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList = new ArrayList<>();
        Document document = null;
        int page = 1;
        Vacancy vacancy;
        while (true)
        {
            try {
                document = getDocument(searchString, page++);
            } catch (IOException e) {
            }
            if (document == null)
                break;
            Elements elements = document.getElementsByClass("job");
            if (!elements.isEmpty())
            {
                for (Element element : elements)
                {
                    vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByClass("title").text());
                    if (element.getElementsByClass("salary").text() != null)
                        vacancy.setSalary(element.getElementsByClass("salary").text());
                    else
                        vacancy.setSalary("");
                    vacancy.setCity(element.getElementsByClass("location").text());
                    vacancy.setCompanyName(element.getElementsByClass("company_name").select("a").text());
                    vacancy.setSiteName("moikrug.ru");
                    vacancy.setUrl("https://" + vacancy.getSiteName() + element.getElementsByClass("title").select("a").attr("href"));
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
        Document document = Jsoup.connect(String.format(URL_FORMAT, page, searchString))
                .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36")
                .referrer("none")
                .get();
        return document;
    }
}
