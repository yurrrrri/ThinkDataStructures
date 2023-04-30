package com.yuri.thinkTest;

import com.yuri.think.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class MyArrayListTest {

    private MyArrayList<Integer> myList;

    @BeforeEach
    public void setUp() throws Exception {
        myList = new MyArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
    }

    @Test
    public void get() {
        assertThat(myList.get(1)).isEqualTo(2);
    }

    @Test
    public void add() {
        assertThat(myList.size()).isEqualTo(3);
    }

    @Test
    public void addIndex() {
        myList.add(1, 5);
        assertThat(myList.get(1)).isEqualTo(5);
        assertThat(myList.size()).isEqualTo(4);
    }

    @Test
    public void addIndexException() {
        assertThatThrownBy(() -> myList.add(-1, 5)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> myList.add(5, 5)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void addAll() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        myList.addAll(list);
        assertThat(myList.size()).isEqualTo(6);
        assertThat(myList.get(4)).isEqualTo(20);
    }

    @Test
    public void clear() {
        myList.clear();
        assertThat(myList.size()).isEqualTo(0);
    }

    @Test
    public void indexOf() {
        assertThat(myList.indexOf(1)).isEqualTo(0);
        assertThat(myList.indexOf(2)).isEqualTo(1);
        assertThat(myList.indexOf(3)).isEqualTo(2);
    }

    @Test
    public void indexOfNull() {
        assertThat(myList.indexOf(null)).isEqualTo(-1);
        myList.add(null);
        assertThat(myList.indexOf(null)).isEqualTo(3);
    }

    @Test
    public void lastIndexOf() {
        myList.add(3);
        myList.add(3);
        myList.add(3);
        assertThat(myList.lastIndexOf(3)).isEqualTo(5);
    }

    @Test
    public void contains() {
        assertThat(myList.contains(2)).isTrue();
        assertThat(myList.contains(10)).isFalse();
    }

    @Test
    public void containsAll() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(myList.containsAll(list)).isTrue();
    }

    @Test
    public void isEmpty() {
        assertThat(myList.isEmpty()).isFalse();
        myList.clear();
        assertThat(myList.isEmpty()).isTrue();
    }

    @Test
    public void remove() {
        assertThat(myList.remove(1)).isEqualTo(2);
    }

    @Test
    public void removeT() {
        MyArrayList<String> strList = new MyArrayList<>();
        strList.add("hi");
        strList.add("hello");

        assertThat(strList.size()).isEqualTo(2);
        assertThat(strList.remove("hi")).isTrue();
        assertThat(strList.size()).isEqualTo(1);
        assertThat(strList.get(0)).isEqualTo("hello");
    }

    @Test
    public void removeAll() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        myList.removeAll(list);
        assertThat(myList.size()).isEqualTo(0);
    }

    @Test
    public void set() {
        assertThat(myList.set(1, 5)).isEqualTo(2);
        assertThat(myList.get(1)).isEqualTo(5);
    }

}
