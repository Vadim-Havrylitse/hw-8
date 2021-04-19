package com.company;

import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private Node<K, V>[] hashTable;
    private int size;
    private int threshold;

    public MyHashMap() {
        hashTable = new Node[16];
        size = 0;
        instanceOfThreshold();
    }

    private void instanceOfThreshold() {
        threshold = (int) (hashTable.length * 0.8);
    }

    private int hash(K key) {
        return Objects.hashCode(key) % (hashTable.length-1);
    }

    //    private Node<K, V> getNecessaryNode(int index) {
//        if (hashTable[index] == null) {
//            throw new NullPointerException("There no data!");
//        }
//        Node<K, V> temp = hashTable[index];
//        while (temp.next != null) {
//            temp = temp.next;
//        }
//        return temp;
//
//    }
    private void overrideMyHashMapWithMoreCapacity() {
        Node<K, V>[] tempHashTable = hashTable;
        hashTable = new Node[2 * tempHashTable.length];
        instanceOfThreshold();
        size = 0;
        System.out.println("overrideMyHashMapWithMoreCapacity");
        for (int i = 0; i < tempHashTable.length; i++) {
            if (tempHashTable[i] == null) {
                continue;
            }
            Node<K, V> tempNode = tempHashTable[i];
            while (tempNode != null) {
                put(tempNode.pair.key, tempNode.pair.value);
                tempNode = tempNode.next;
            }

        }

    }


    @Override
    public boolean put(K key, V value) {
        if (size == threshold) {
            overrideMyHashMapWithMoreCapacity();
        }
        Node<K, V> nodeNew = new Node<>(key, value);
        int index = hash(key);
        if (hashTable[index] == null) {
            hashTable[index] = nodeNew;
            size++;
        } else {
            Node<K, V> temp = hashTable[index];
            while (true) {
                if (temp.pair.key == key && temp.pair.value != value) {
                    temp.pair.value = value;
                    return true;
                }
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = nodeNew;
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(K key) {
        int index = hash(key);
        if (hashTable[index] == null) {
            return false;
        }
        if (hashTable[index].next == null) {
            hashTable[index] = null;
            size--;
            return true;
        }
        Node<K, V> temp = hashTable[index];
        while (temp != null) {
            if (temp.pair.key.equals(key)) {
                temp = temp.next;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null) {
                continue;
            }
            hashTable[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        if (hashTable[index] == null) {
            throw new IllegalArgumentException("Not found key!!!!!!");
        }
        if (hashTable[index].next == null) {
            return hashTable[index].pair.value;
        }
        Node<K, V> temp = hashTable[index];
        while (temp != null) {
            if (temp.pair.key.equals(key)) {
                return temp.pair.value;
            }
            temp = temp.next;
        }
        throw new IllegalArgumentException("Not found key!!!!!!");
    }


    private class Node<K, V> {
        public Entry<K, V> pair;
        public Node<K, V> next;

        public Node(K key, V value) {
            pair = new Entry<>(key, value);
            next = null;
        }

        private void clearAll() {
            pair.key = null;
            pair.value = null;
            next = null;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hashCode() == node.hashCode() && Objects.equals(pair.key, node.pair.key) && Objects.equals(pair.value, node.pair.value);
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * 7 + Objects.hashCode(pair.key);
            return hash;
        }

        private class Entry<K, V> {
            public K key;
            public V value;

            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

    }


}
