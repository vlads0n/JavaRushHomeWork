package com.javarush.test.level19.lesson03.task04;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1978

Подсказка: воспользуйтесь классом Calendar
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner{

        private Scanner scanner;

        PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        public Person read()
        {
            String[] a = new String[6];
            if (scanner.hasNext())
            {
                a = scanner.nextLine().split(" ");
            }
            int day = Integer.parseInt(a[3]);
            int month = Integer.parseInt(a[4]) - 1;
            int year = Integer.parseInt(a[5]);
            Calendar calendar = new GregorianCalendar(year, month, day);
            Date birthDate = calendar.getTime();
            Person person = new Person(a[1], a[2], a[0], birthDate);

            return person;
        }

        public void close()
        {
            scanner.close();
        }
    }

    public static void main(String[] args) throws IOException {
        PersonScanner adapter = new PersonScannerAdapter(new Scanner(new File ("c:\\VLAD.txt"), "Cp1251"));

        System.out.println(adapter.read());
        adapter.close();

    }
}
