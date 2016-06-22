package com.javarush.test.level35.lesson10.bonus01;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException {
        Set<? extends Animal> allAnimals = getAllAnimals("C:\\Users\\Vinnik\\Documents\\JavaRushHomeWork\\src\\com\\javarush\\test\\level35\\lesson10\\bonus01\\data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException {
        Set<Animal> resultSet = new HashSet<>();
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";
        File dir = new File(pathToAnimals);
        String[] classFiles = dir.list();
        for (String file : classFiles) {
            final String finalPath = dir.getAbsolutePath() + File.separator;
            ClassLoader loader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String className) throws ClassNotFoundException {
                    byte[] temp = getBytesFromFile(finalPath + className + ".class");
                    return defineClass(null, temp, 0, temp.length);
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

            Class clazz = loader.loadClass(file.substring(0, file.lastIndexOf(".")));
            if (Animal.class.isAssignableFrom(clazz)) {
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for (Constructor constructor : constructors) {
                    if (constructor.getParameterTypes().length == 0 && Modifier.isPublic(constructor.getModifiers())) {
                        constructor.setAccessible(true);
                        try {
                            resultSet.add((Animal) constructor.newInstance(null));
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return resultSet;
    }
}
