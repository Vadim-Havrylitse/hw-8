package com.company;

import java.util.Iterator;

public class MyQueueClass<Q> implements MyQueue<Q> {

    private Node<Q> head;
    private int size;

    public MyQueueClass() {
        this.head = new Node<>(null, null);
        this.size = 0;
    }

    @Override
    public boolean add(Q value) {
        if (size == 0) {
            size++;
            head.next = new Node<>(value, null);
            return true;
        }
        Node<Q> temp = getNecessaryNode(size++);
        temp.next = new Node<>(value, null);
        return true;
    }

    @Override
    public Q remove(int index) {
        Q removeEl;
        if (size == 1) {
            size--;
            removeEl = head.next.element;
            head.next = null;
            return removeEl;
        }
        if (index == size-1) {
            size--;
            Node<Q> temp = getNecessaryNode(index);
            removeEl = temp.next.element;
            temp.next = null;
            return removeEl;
        }
        Node<Q> temp = getNecessaryNode(index);
        removeEl = temp.next.element;
        temp.next = temp.next.next;
        size--;
        return removeEl;

    }

    @Override
    public void clear() {
            head.next = null;
            size = 0;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Q peek() {
        if (size == 0){
            throw new NullPointerException("There no found element. Please add anything!");
        }
        return head.next.element;
    }

    @Override
    public Q poll() {
        if (size == 0){
            peek();
        }
        return remove(0);
    }

    @Override
    public Iterator<Q> iterator() {
        return new Iterator<Q>() {
            int cursor = 0;
            Node<Q> temp = head;

            @Override
            public boolean hasNext() {
                if (cursor++ < size) {
                    return true;
                } else {
                    cursor = 0;
                    return false;
                }
            }

            @Override
            public Q next() {
                temp = temp.next;
                return temp.element;
            }
        };
    }

    private Node<Q> getNecessaryNode (int index){
        Node<Q> necessaryNode = head;
        for (int i = 0; i < index; i++) {
            necessaryNode = necessaryNode.next;
        }
        return necessaryNode;
    }

    private static class Node<Q> {
        public Q element;
        public Node<Q> next;

        public Node(Q element, Node<Q> next) {
            this.element = element;
            this.next = next;
        }

    }
}
