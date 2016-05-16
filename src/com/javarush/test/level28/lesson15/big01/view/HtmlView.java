package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

/**
 * Created by Влад on 18.01.2016.
 */
public class HtmlView implements View {

    private Controller controller;
    private final String filePath = this.getClass().getPackage().toString()
            .replaceAll("\\.", "/")
            .replaceFirst("package ", "./src/")
            + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
        getUpdatedFileContent(vacancies);
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {

        Document document = null;
        try {
            document = getDocument();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }

        Element original = document.getElementsByClass("template").first();
        Element template = original.clone();
        template.removeClass("template").removeAttr("style");
        document.select("tr[class=\"vacancy\"]").remove();

        for (Vacancy vacancy : vacancies) {
            Element element = template.clone();
            element.getElementsByClass("city").get(0).text(vacancy.getCity());
            element.getElementsByClass("companyName").get(0).text(vacancy.getCompanyName());
            element.getElementsByClass("salary").get(0).text(vacancy.getSalary());
            element.getElementsByAttribute("href").get(0).attr("href", vacancy.getUrl()).text(vacancy.getTitle());

            original.before(element.outerHtml());
        }

        return document.html();
    }

    private void updateFile(String fileBody) throws IOException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
        printWriter.write(fileBody);
        printWriter.close();
    }

    protected Document getDocument() throws IOException {
        File file = new File(filePath);
        return Jsoup.parse(file, "UTF-8");
    }
}
