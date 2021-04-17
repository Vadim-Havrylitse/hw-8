package com.company;

public interface MyStack<S> extends Iterable<S>{
    S push(S value);// добавляет элемент в конец
    S remove(int index);// удаляет элемент под индексом
    void clear();// очищает коллекцию
    int size();// возвращает размер коллекции
    S peek();// возвращает первый элемент в стеке (LIFO)
    S pop();// возвращает первый элемент в стеке и удаляет его из коллекции

}
