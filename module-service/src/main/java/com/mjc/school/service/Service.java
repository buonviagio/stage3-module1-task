package com.mjc.school.service;

import java.util.List;

public interface Service<T, A> {
    List<T> getAllNews();
    T getNewsById(Long id);

    T create(T t);

    T update(T t);

    boolean deleteById(Long id);
}
