package com.company;

import java.util.Iterator;

public class MyLinkedList<R> implements MyList<R> {

    private Node<R> firstNode;
    private Node<R> lastNode;
    private int size;

    public MyLinkedList() {
        this.lastNode = new Node<R>(null, firstNode, null);
        this.firstNode = new Node<R>(null, null, lastNode);
        this.size = 0;
    }


    public void addLast(R el) {
        Node<R> previous = lastNode;
        previous.element = el;
        previous.prev = lastNode.prev;
        lastNode = new Node<R>(null, previous, null);
        previous.next = lastNode;
        size++;
    }


    @Override
    public void add(R value) {
        addLast(value);
    }

    public void addByIndex(int index, R value) {
        Node<R> temp = firstNode;
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
        Node<R> temp = firstNode.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        if (index != 0) {
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
            size--;
        } else {
            temp.next.prev = temp.prev;
            firstNode.next = temp.next;
            size--;
        }

    }

    @Override
    public void clear() {
        firstNode.next = lastNode;
        lastNode.prev = firstNode;
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
        Node<R> temp = firstNode.next;
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
