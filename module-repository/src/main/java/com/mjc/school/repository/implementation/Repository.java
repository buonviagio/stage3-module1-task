package com.mjc.school.repository.implementation;

import java.util.List;

public interface Repository <T, A> {
    List<T> readAllNews();
    List<A> getAllAuthors();

    T readById(int id);

    T create(T t);

    T update(T t);

    boolean deleteById(int id);
}
