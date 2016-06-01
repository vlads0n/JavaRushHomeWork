package com.javarush.test.level20.lesson10.bonus04;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution extends AbstractList<String> implements List<String>, Cloneable, Serializable{

    private ArrayList<Node<String>> heap = new ArrayList<>();
    private int heapSize = 0;

    public Solution() {
        add("0");
    }

    private class Itr implements Iterator {
        int cursor = 1;
        int lastRet = -1;
        int exceptedModCount = modCount;

        public Itr() {
            while (cursor < heap.size()) {
                if (heap.get(cursor) == null)
                    cursor++;
                else break;
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != heap.size() && heap.size() > 1;
        }

        @Override
        public Object next() {
            checkForModification();
            try {
                int i = cursor;
                String next = heap.get(i).item;
                lastRet = i;
                cursor++;
                while (cursor < heap.size() && heap.get(cursor) == null)
                    cursor++;
                return next;
            }
            catch (IndexOutOfBoundsException e) {
                checkForModification();
                throw new NoSuchElementException();
            }
            catch (NullPointerException e) {
                System.out.println("NullPointerException:");
                System.out.println("Cursor:" + cursor);
                for (int i = 0; i < heap.size(); i++) {
                    System.out.println(heap.get(i));
                }
                throw e;
            }
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForModification();

            try {
                Solution.this.remove(heap.get(lastRet).item);
                if (lastRet == cursor) {
                    while (cursor < heap.size() && heap.get(cursor) == null)
                        cursor++;
                }
                lastRet = -1;
                exceptedModCount = modCount;
            }
            catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForModification() {
            if (modCount != exceptedModCount)
                throw  new ConcurrentModificationException();
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    private static class Node<E> implements Serializable {
        E item;
        int listArrayIndex;
        Node<E> parent;
        Node<E> childL;
        Node<E> childR;

        public Node(E item, Node<E> parent, Node<E> childL, Node<E> childR) {
            this.item = item;
            this.parent = parent;
            this.childL = childL;
            this.childR = childR;
        }

        @Override
        public String toString() {
            return "Value =" + item + ", heapIndex = " + listArrayIndex + ";";
        }
    }

    private Node<String> lastParent() {
        for (int i = (heap.size() - 1) / 2; i < heap.size(); i++) {
            if (heap.get(i) != null && (heap.get(i).childL == null || heap.get(i).childR == null))
                return heap.get(i);
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;

        for (Node<String> aHeap : heap) {
            if (aHeap != null && aHeap.item.equals(o)) {
                result = removeWithChilds(aHeap);
                break;
            }
        }

        for (int i = heap.size() - 1; i > 0; i--) {
            if (heap.get(i) == null)
                heap.remove(i);
            else
                break;
        }

        return result;
    }

    public boolean removeWithChilds(Node<String> node) {}

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public String[] toArray() {
        String[] result = new String[][size];
        int i = 0;
        for (String x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        String[] result = a;
        for (Object x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean add(String s) {
        return super.add(s);
    }

    @Override
    public void add(int index, String element) {
        super.add(index, element);
    }



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public String set(int index, String element) {
        return super.set(index, element);
    }

    @Override
    public String remove(int index) {
        return super.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return super.addAll(index, c);
    }

    @Override
    public ListIterator<String> listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return super.subList(fromIndex, toIndex);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public String get(int index) {
        return null;
    }

    public String getParent(String value) {
        //have to be implemented
        return null;
    }

    public static void main(String[] args) {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
    }
}
