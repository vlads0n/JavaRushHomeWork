package com.javarush.test.level33.lesson10.bonus01;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        String resultXML = null;
        try {
            StringWriter stringWriter = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, stringWriter);

            resultXML = stringWriter.toString();
            String openTag = "<" + tagName;
            String closingTag = "</" + tagName;
            int indexOfOpenTag = resultXML.indexOf(openTag);
            int indexOfClosingTag = resultXML.indexOf(closingTag);
            int tagLength = tagName.length();
            String commentary = "\t<!--" + comment + "-->\n\t";

            while (indexOfOpenTag != -1 && indexOfClosingTag != -1) {
                if (indexOfOpenTag < indexOfClosingTag) {
                    resultXML = resultXML.substring(0, indexOfOpenTag - 1) + commentary + resultXML.substring(indexOfOpenTag - 1);
                    tagLength += commentary.length();
                    indexOfOpenTag = resultXML.indexOf(tagName, tagLength + indexOfOpenTag);
                    indexOfClosingTag = resultXML.indexOf(tagName, tagLength + indexOfClosingTag);
                }
                else if (indexOfOpenTag > indexOfClosingTag)
                {
                    resultXML = resultXML.substring(0, indexOfOpenTag - 2) + commentary + resultXML.substring(indexOfOpenTag - 2);
                    tagLength += commentary.length();
                    indexOfOpenTag = resultXML.indexOf(tagName, tagLength + indexOfOpenTag);
                    indexOfClosingTag = resultXML.indexOf(tagName, tagLength + indexOfClosingTag);
                }
            }
        } catch (JAXBException e) {}

        return resultXML;
    }

    public static void main(String[] args) {
       String result = toXmlWithComment(new AnExample(), "needCDATA", "it's a comment - <needCDATA>");
        System.out.println(result);
    }
}
