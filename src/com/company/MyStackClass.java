package com.company;

import java.util.Iterator;

public class MyStackClass<S> implements MyStack<S> {
    private Node<S> lastNode;
    private int size;

    public MyStackClass() {
        this.lastNode = new Node<S>();
        this.size = 0;
    }

    @Override
    public S push(S value) {
        if (size == 0) {
            lastNode.prev = new Node<>(value, null);
            size++;
            return value;
        }
        lastNode.prev = new Node<>(value, lastNode.prev);
        size++;
        return value;
    }

    @Override
    public S remove(int index) {
        S removeEl = getNecessaryNode(index).element;
        if (index == 0) {
            getNecessaryNode(1).prev = null;
            size--;
            return removeEl;
        }
        getNecessaryNode(index + 1).prev = getNecessaryNode(index - 1);
        size--;
        return removeEl;
    }

    @Override
    public void clear() {
        lastNode.prev = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public S peek() {
        return lastNode.prev.element;
    }

    @Override
    public S pop() {
        return remove(size - 1);
    }

    @Override
    public Iterator<S> iterator() {
        return new Iterator<S>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (size - cursor > 0) {
                    return true;
                } else {
                    cursor = 0;
                    return false;
                }
            }

            @Override
            public S next() {
                cursor++;
                return getNecessaryNode(size - cursor).element;
            }
        };
    }


    private Node<S> getNecessaryNode(int index) {
        Node<S> necessaryNode = lastNode;
        for (int i = 0; i < size - index; i++) {
            necessaryNode = necessaryNode.prev;
        }
        return necessaryNode;
    }

    private static class Node<S> {
        public S element;
        public Node<S> prev;

        public Node() {
            this(null, null);
        }

        public Node(S element, Node<S> prev) {
            this.element = element;
            this.prev = prev;
        }

    }
}
