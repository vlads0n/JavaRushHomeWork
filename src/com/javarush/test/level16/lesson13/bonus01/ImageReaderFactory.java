package com.javarush.test.level16.lesson13.bonus01;


import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by Влад on 16.02.2015.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes types)
    {
         if (types == ImageTypes.BMP)
             return new BmpReader();
         else if (types == ImageTypes.PNG)
             return new PngReader();
         else if (types == ImageTypes.JPG)
             return new JpgReader();
         else
             throw  new  IllegalArgumentException("Неизвестный тип картинки");
    }
}
