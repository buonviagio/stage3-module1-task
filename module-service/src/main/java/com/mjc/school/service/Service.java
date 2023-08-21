package com.mjc.school.service;

import java.util.List;

public interface Service<T, A> {
    List<T> readAllNews();
    T readById(Long id);

    T create(T t);

    T update(T t);

    Boolean deleteById(Long id);
}
