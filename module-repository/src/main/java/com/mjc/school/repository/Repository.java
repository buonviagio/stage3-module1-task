package com.mjc.school.repository;

import java.util.List;

public interface Repository <T, A> {
    List<T> getAllNews();
    List<A> getAllAuthors();

    T getNewsById(int id);

    T create(T t);

    T update(T t);

    boolean deleteById(int id);
}
