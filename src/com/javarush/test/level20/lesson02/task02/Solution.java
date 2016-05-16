package com.javarush.test.level20.lesson02.task02;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            OutputStream outputStream = new FileOutputStream("c:\\VLAD.txt");
            InputStream inputStream = new FileInputStream("c:\\VLAD.txt");

            JavaRush javaRush = new JavaRush();
            User user = new User();

            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setBirthDate(new Date());
            user.setCountry(User.Country.UKRAINE);
            user.setMale(true);

            javaRush.users.add(user);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            System.out.println(loadedObject.equals(javaRush));


            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            String isUsers = (users != null) ? "yes" : "no";
            int count = users.size();
            printWriter.println(isUsers);
            printWriter.println(count);
            if (isUsers.equals("yes"))
            {
                for (User user: users)
                {
                    if (user != null)
                    {
                        printWriter.println(user.getFirstName());
                        printWriter.println(user.getLastName());
                        printWriter.println(user.getBirthDate());
                        printWriter.println(user.getCountry());
                        printWriter.println(user.isMale());
                    }
                }
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String isUsers = reader.readLine();
            int count = Integer.parseInt(reader.readLine());
            if (isUsers.equals("yes"))
            {
                for (int i = 0; i < count; i++)
                {
                    User user = new User();

                    String firstName = reader.readLine();
                    String lastName = reader.readLine();
                    Date dateBirthday = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(reader.readLine());
                    String country = reader.readLine();
                    String male = reader.readLine();

                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setBirthDate(dateBirthday);
                    if (country.equals("UKRAINE"))
                        user.setCountry(User.Country.UKRAINE);
                    else if (country.equals("RUSSIA"))
                        user.setCountry(User.Country.RUSSIA);
                    else if (country.equals("OTHER"))
                        user.setCountry(User.Country.OTHER);
                    if (male.equals("true"))
                        user.setMale(true);
                    else
                        user.setMale(false);
                    users.add(user);
                }
            }
            reader.close();
        }
    }
}
