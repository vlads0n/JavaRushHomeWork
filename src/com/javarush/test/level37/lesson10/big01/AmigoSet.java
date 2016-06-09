package com.javarush.test.level37.lesson10.big01;

import java.io.Serializable;
import java.util.*;


/**
 * Created by Влад on 09.06.2016.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {
    private final static Object PRESENT = new Object();
    private HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>((int) Math.max(16, collection.size() / .75f));
        this.addAll(collection);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean add(E e) {
        try {
            map.put(e, PRESENT);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
