package com.yuri.think;

import java.util.*;

public class MyLinkedList<T> implements List<T> {

    private class Node {
        public T data;
        public Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    private Node head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        Node node = getNode(index);
        return node.data;
    }

    private Node getNode(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;

        for(int i=0; i<index; i++) {
            node = node.next;
        }

        return node;
    }

    @Override
    public boolean add(T element) {
        if(head == null) {
            head = new Node(element);
        } else {
            Node node = head;
            while(node.next != null) {
                node = node.next;
            }
            node.next = new Node(element);
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if(index == 0) {
            head = new Node(element, head);
        } else {
            Node node = getNode(index - 1);
            node.next = new Node(element, node.next);
        }
        size++;
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
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(Object obj : collection) {
            if(!contains(obj)) return false;
        }
        return true;
    }

    @Override
    public int indexOf(Object obj) {
        Node node = head;

        for(int i=0; i<size; i++) {
            if(equals(obj, node.data)) return i;
            node = node.next;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        Node node = head;
        int index = -1;

        for(int i=0; i<size; i++) {
            if(equals(obj, node.data)) index = i;
            node = node.next;
        }

        return index;
    }

    private boolean equals(Object target, Object element) {
        if(target == null) {
            return element == null;
        }
        return target.equals(element);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T remove(int index) {
        T element = get(index);

        if(index == 0) {
            head = head.next;
        } else {
            Node node = getNode(index - 1);
            node.next = node.next.next;
        }

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
        Node node = getNode(index);
        T oldElement = node.data;
        node.data = element;
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        MyLinkedList<T> list = new MyLinkedList<>();
        for (Node node = head; node != null; node = node.next) {
            if (i >= fromIndex && i <= toIndex) {
                list.add(node.data);
            }
            i++;
        }
        return list;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (Node node = head; node != null; node = node.next) {
            array[i] = node.data;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        T[] array = (T[]) toArray();
        return Arrays.asList(array).iterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

}
