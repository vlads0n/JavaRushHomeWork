package com.javarush.test.level20.lesson10.bonus04;

import java.io.*;
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

        public Node(Node<E> childL, Node<E> childR, E item, Node<E> parent) {
            this.item = item;
            this.childL = childL;
            this.childR = childR;
            this.parent = parent;
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
                result = removeWithChildren(aHeap);
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

    private boolean removeWithChildren(Node<String> node) {
        if (node.childR != null)
            removeWithChildren(node.childR);
        if (node.childL != null)
            removeWithChildren(node.childL);
        if (node.listArrayIndex == heap.size() - 1)
            heap.remove(heap.size() - 1);
        Node<String> parent = node.parent;

        if (parent != null) {
            if (parent.childL == node) {
                parent.childL = parent.childR;
                parent.childR = null;
            }
            if (parent.childR == node)
                parent.childR = null;
        }
        if (node.listArrayIndex < heap.size())
            heap.set(node.listArrayIndex, null);
        heapSize--;
        return true;
    }

    @Override
    public boolean add(String s) {
        Node<String> parent = lastParent();
        Node<String> newNode = new Node<>(null, null, s, parent);

        if(parent != null) {
            if (parent.childL == null)
                parent.childL = newNode;
            else
                parent.childR = newNode;
        }

        newNode.listArrayIndex = heap.size();
        heap.add(newNode);
        heapSize++;
        return true;
    }

    @Override
    public int size() {
        if (heapSize == 0)
            return 0;
        else
            return heapSize - 1;
    }

    public String getParent(String value) {
        String parent = null;

       for (int i = 0; i < heapSize; i++) {
            if (heap.get(i) != null && heap.get(i).item.equals(value))
                parent = heap.get(i).parent.item;
       }

        if (parent != null && parent.equals("0"))
            return null;

        return parent;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        Iterator<String> iterator = this.iterator();
        while (iterator.hasNext())
            remove(iterator.next());
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this.size() != ((Solution) o).size())
            return false;
        Iterator<String> iterator1 = this.iterator();
        Iterator<String> iterator2 = ((Solution) o).iterator();
        while (iterator1.hasNext()) {
            if (!iterator1.next().equals(iterator2.next()))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < heapSize; i++) {
            if (heap.get(i) != null && heap.get(i).item.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        String result = "";
        for (Node<String> aHeap : heap) {
            if (aHeap != null)
                result += "<(" + aHeap.parent + ")" + aHeap.item + ">\n";
        }
        return result;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[heapSize];
        int i = 0;
        for (Node<String> aHeap : heap)
            result[i++] = aHeap.item;
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        for (String element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            remove(element);
        }
        return true;
    }

    @Override
    protected Solution clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(this);
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            return (Solution) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        // Write out any hidden serialization magic
        s.defaultWriteObject();

        // Write out size
        s.writeInt(heapSize);

        // Write out all elements in the proper order.
        for (Node<String> aHeap : heap)
            s.writeObject(aHeap.item);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        // Read in any hidden serialization magic
        s.defaultReadObject();

        // Read in size
        int size = s.readInt();

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++)
            add((String) s.readObject());
    }

    public static void main(String[] args) {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
           list.add(String.valueOf(i));
        }
        System.out.println(list);
        list.remove("2");
        list.remove("9");
        System.out.println(list);
        list.add("16");
        list.add("17");
        list.add("18");
        list.add("19");
        list.add("20");
        System.out.println(list);
    }
}
