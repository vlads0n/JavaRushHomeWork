package com.javarush.test.level36.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Solution solution = new Solution("C:\\Users\\Vinnik\\Documents\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File dir = new File(packageName);
        String[] files = dir.list();

        for (String fileName : files) {
            final String finalPath = dir.getAbsolutePath() + File.separator;
            ClassLoader classLoader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    byte[] bytes = getBytesFromFile(finalPath + name + ".java");
                    return defineClass(null, bytes, 0, bytes.length);
                }

                private byte[] getBytesFromFile(String fileName) {
                    File file = new File(fileName);
                    FileInputStream fis;
                    try {
                        fis = new FileInputStream(file);
                    } catch (Exception e) {
                        return null;
                    }
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    int size = (int) file.length();
                    byte[] b = new byte[size];
                    int rb = 0;
                    int chunk;
                    try {
                        while ((size - rb) > 0) {
                            chunk = bis.read(b, rb, size - rb);
                            if (chunk == -1) {
                                break;
                            }
                            rb += chunk;
                        }
                    } catch (IOException e) {
                        try {
                            throw new ClassNotFoundException();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        return null;
                    }
                    return b;
                }
            };

            Class clazz = classLoader.loadClass(fileName.substring(0, fileName.lastIndexOf(".")));
            if (HiddenClass.class.isAssignableFrom(clazz))
                hiddenClasses.add(clazz);
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        HiddenClass result = null;
        for (Class clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                for (Constructor constructor : clazz.getDeclaredConstructors()) {
                    if (constructor.getParameterCount() == 0) {
                        constructor.setAccessible(true);
                        result = (HiddenClass) constructor.newInstance();
                    }
                }
            }
        }
        return result;
    }
}
