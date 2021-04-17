package com.company;

public interface MyMap<K, V>{
    boolean put(K key, V value); //добавляет пару ключ + значение
    boolean remove(K key); //удаляет пару по ключу
    void clear(); //очищает коллекцию
    int size(); //возвращает размер коллекции
    V get(K key); //возвращает значение(Object value) по ключу
}
