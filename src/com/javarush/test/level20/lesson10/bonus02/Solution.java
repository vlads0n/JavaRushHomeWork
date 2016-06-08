package com.javarush.test.level20.lesson10.bonus02;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int downLine = 0;
        int downRow = 0;
        int upLine;
        int upRow;
        int count = 0;
        for (int row = 0; row < a.length; row++) {
            for (int line = 0; line < a.length; line++) {
                if (a[line][row] == 1) {
                    upLine = line;
                    upRow = row;
                    for (int k = upLine; k < a.length; ) {
                        downLine = k;
                        if (a[k][upRow] == 1)
                            k++;
                        else if (a[k][upRow] == 0) {
                            downLine = k - 1;
                            break;
                        }
                    }
                    for (int k = upRow; k < a.length; ) {
                        downRow = k;
                        if (a[downLine][k] != 0)
                            k++;
                        else if (a[downLine][k] == 0) {
                            downRow = k - 1;
                            break;
                        }
                    }
                    for (int i = upLine; i < downLine + 1; i++) {
                        for (int j = upRow; j < downRow + 1; j++) {
                            a[i][j] = 0;
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }
}
