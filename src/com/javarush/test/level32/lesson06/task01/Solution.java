package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add((byte) (Math.random() * 10 + 48));
            list.add((byte) (Math.random() * 26 + 65));
            list.add((byte) (Math.random() * 26 + 97));
        }
        Collections.shuffle(list);
        list.remove(list.size() - 1);
        for (Byte bytes : list)
            byteArrayOutputStream.write(bytes);
        return byteArrayOutputStream;
    }
}
