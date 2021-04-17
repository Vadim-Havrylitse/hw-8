package com.company;

import java.util.Iterator;

public class MyLinkedList<R> implements MyList<R> {

    private Node<R> firstNoda;
    private Node<R> lastNoda;
    private int size;

    public MyLinkedList() {
        this.lastNoda = new Node<R>(null, firstNoda, null);
        this.firstNoda = new Node<R>(null, null, lastNoda);
        this.size = 0;
    }


    public void addLast(R el) {
        Node<R> previous = lastNoda;
        previous.element = el;
        previous.prev = lastNoda.prev;
        lastNoda = new Node<R>(null, previous, null);
        previous.next = lastNoda;
        size++;
    }


    @Override
    public void add(R value) {
        addLast(value);
    }

    public void addByIndex(int index, R value) {
        Node<R> temp = firstNoda;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
            if (i == index) {
                temp.next = new Node<R>(temp.element, temp, temp.next);
                temp.element = value;
                size++;
            }
        }
    }

    @Override
    public void remove(int index) {
        Node<R> temp = firstNoda.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        if (index != 0) {
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
            size--;
        } else {
            temp.next.prev = temp.prev;
            firstNoda.next = temp.next;
            size--;
        }

    }

    @Override
    public void clear() {
        firstNoda.next = lastNoda;
        lastNoda.prev = firstNoda;
        size = 0;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public R get(int index) {
        if (index >= size) {
            throw new NullPointerException("This index not found!!!!");
        }
        Node<R> temp = firstNoda.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.element;
    }

    @Override
    public Iterator<R> iterator() {
        return new Iterator<R>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public R next() {
                return get(cursor++);
            }
        };
    }

    // наша нода для двухсвязного списка
    private static class Node<R> {
        public R element;
        public Node<R> prev;
        public Node<R> next;

        public Node(R element, Node<R> prev, Node<R> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
