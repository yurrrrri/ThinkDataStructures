package com.yuri.think;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private int size;
    private T[] array;

    public MyArrayList() {
        size = 0;
        array = (T[]) new Object[10];
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public boolean add(T element) {
        if(size >= array.length) {
            T[] copyArray = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, copyArray, 0, size);
            array = copyArray;
        }

        array[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        add(element);

        for(int i=size-1; i>index; i--) {
            array[i] = array[i-1];
        }

        array[index] = element;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean flag = true;

        for(T element : collection) {
            flag &= add(element);
        }

        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for(int i=0; i<size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object obj) {
        for(int i=0; i<size; i++) {
            if(equals(obj, array[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object target) {
        for (int i=size-1; i>=0; i--) {
            if (equals(target, array[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean equals(Object target, Object element) {
        if(target == null) {
            return element == null;
        }
        return target.equals(element);
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(Object element : collection) {
            if(!contains(element)) return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T remove(int index) {
        T element = get(index);

        for(int i=index; i<size-1; i++) {
            array[i] = array[i+1];
        }

        array[size-1] = null;
        size--;
        return element;
    }

    @Override
    public boolean remove(Object obj) {
        int index = indexOf(obj);

        if(index == -1) return false;

        remove(index);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;

        for(Object obj : collection) {
            flag &= remove(obj);
        }

        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        T oldElement = get(index);
        array[index] = element;
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> subList(int from, int to) {
        if(from < 0 || to >= size || from > to) {
            throw new IndexOutOfBoundsException();
        }
        T[] copyArray = Arrays.copyOfRange(array, from, to);
        return Arrays.asList(copyArray);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <U> U[] toArray(U[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        T[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).iterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        T[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        T[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).listIterator(index);
    }

}
