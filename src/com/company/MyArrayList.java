package com.company;

import java.util.Arrays;
import java.util.Iterator;

    public class MyArrayList<T> implements MyList<T> {
    private Object[] arr;
    private int size;
    private int cursore;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(T[] a) {
        arr = Arrays.copyOf(a, a.length);
        size = a.length;
    }

    public MyArrayList(int capacity){
        arr = new Object[capacity];
        size = 0;

    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }

    @Override
    public void add(T value) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr,size * 3 / 2);
        }
        arr[size++] = value;
    }

    public void add(T[] value) {
        for (T el : value) {
            add(el);
        }
    }

    @Override
    public void remove(int index) {
        if (index > size || index < 0) {
            throw new RuntimeException("It is wrong steps!!!!!!!");
        }
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr [i+1];
        }
        arr = Arrays.copyOf(arr, --size);

    }

    @Override
    public void clear() {
        arr = new Object[14];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            throw new RuntimeException("WHAT are you doing, man? Your index is out of Array");
        }
        return (T) arr[index];
    }


    @Override
    public Iterator<T> iterator() {
        cursore = 0;
        return new Iterator<T>(){

            @Override
            public boolean hasNext() {
                return cursore < size;
            }

            @Override
            public T next() {
                return (T) arr[cursore++];
            }
        };
    }
}
