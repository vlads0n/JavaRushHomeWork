package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same");
        for (Word word : list)
            System.out.println(word.toString());
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        Word text;
        String result;
        int tmpX;
        int tmpY;
        boolean wordIsSearch;
        List<Word> wordList = new ArrayList<>();
        for (String word : words) {
            text = new Word(word);
            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[0].length; x++) {
                    wordIsSearch = false;
                    if ((char) crossword[y][x] == word.charAt(0)) {
                        text.setStartPoint(x, y);
                        for (int i = 1; i <= 8; i++) {
                            tmpX = x;
                            tmpY = y;
                            result = "";
                            switch (i) {
                                case 1:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpX++;
                                            tmpY--;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                                case 2:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpX++;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                                case 3:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpX++;
                                            tmpY++;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                                case 4:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpY++;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                                case 5:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpX--;
                                            tmpY++;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                                case 6:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpX--;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                                case 7:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpX--;
                                            tmpY--;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                                case 8:
                                    try {
                                        for (int j = 0; j < word.length(); j++) {
                                            result += (char) crossword[tmpY][tmpX] + "";
                                            tmpY--;
                                        }
                                        if (result.equals(word)) {
                                            text.setEndPoint(tmpX, tmpY);
                                            wordIsSearch = true;
                                        }
                                        break;
                                    }
                                    catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }
                            }
                        }
                    }
                    if (wordIsSearch)
                        wordList.add(text);
                }
            }
        }
        return wordList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
