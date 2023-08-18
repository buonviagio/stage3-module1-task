package com.mjc.school.service;

import java.util.List;

public interface Service<T, A> {
    List<T> getAllNews();
    List<A> getAllAuthors();
    T getNewsById(int id);

    T create(T t);

    T update(T t);

    boolean deleteById(int id);
}
