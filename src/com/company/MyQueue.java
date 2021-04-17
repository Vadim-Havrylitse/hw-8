package com.company;


public interface MyQueue<Q> extends Iterable<Q> {
    boolean add(Q value);// добавляет элемент в конец
    Q remove(int index);// удаляет элемент под индексом
    void clear();// очищает коллекцию
    int size();// возвращает размер коллекции
    Q peek();// возвращает первый элемент в очереди (FIFO)
    Q poll();// возвращает первый элемент в очереди и удаляет его из коллекции

}
