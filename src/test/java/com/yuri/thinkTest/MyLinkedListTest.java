package com.yuri.thinkTest;

import com.yuri.think.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyLinkedListTest {

    private MyLinkedList<Integer> myList;

    @BeforeEach
    public void setUp() throws Exception {
        myList = new MyLinkedList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
    }

    @Test
    public void get() {
        assertThat(myList.get(1)).isEqualTo(2);
    }

    @Test
    public void getException() {
        assertThatThrownBy(() -> myList.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> myList.get(5)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void add() {
        assertThat(myList.add(4)).isTrue();
        assertThat(myList.size()).isEqualTo(4);
    }

    @Test
    public void addIndex() {
        myList.add(1, 5);

        assertThat(myList.get(1)).isEqualTo(5);
        assertThat(myList.size()).isEqualTo(4);
    }

    @Test
    public void addAll() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertThat(myList.addAll(list)).isTrue();
        assertThat(myList.size()).isEqualTo(6);
    }

    @Test
    public void clear() {
        myList.clear();
        assertThat(myList.size()).isEqualTo(0);
    }

    @Test
    public void contains() {
        assertThat(myList.contains(1)).isTrue();
        assertThat(myList.contains(2)).isTrue();
        assertThat(myList.contains(3)).isTrue();
        assertThat(myList.contains(-1)).isFalse();
        assertThat(myList.contains(5)).isFalse();
    }

    @Test
    public void containsAll() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(myList.containsAll(list)).isTrue();
    }

    @Test
    public void indexOf() {
        assertThat(myList.indexOf(3)).isEqualTo(2);
    }

    @Test
    public void lastIndexOf() {
        myList.add(3);
        myList.add(3);
        myList.add(3);

        assertThat(myList.lastIndexOf(3)).isEqualTo(5);
    }

    @Test
    public void isEmpty() {
        assertThat(myList.isEmpty()).isFalse();
        myList.clear();
        assertThat(myList.isEmpty()).isTrue();
    }

    @Test
    public void remove() {
        assertThat(myList.remove(2)).isEqualTo(3);
    }

    @Test
    public void removeT() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("hi");
        list.add("hello");

        assertThat(list.remove("hi")).isTrue();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void removeAll() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(myList.removeAll(list)).isTrue();
        assertThat(myList.size()).isEqualTo(0);
    }

    @Test
    public void set() {
        assertThat(myList.set(1, 5)).isEqualTo(2);
        assertThat(myList.get(1)).isEqualTo(5);
    }
}
