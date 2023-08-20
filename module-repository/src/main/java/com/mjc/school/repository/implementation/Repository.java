package com.mjc.school.repository.implementation;

import java.util.List;

public interface Repository <T> {
    List<T> readAllNews();

    T readById(Long id);

    T create(T t);

    T update(T t);

    Boolean deleteById(Long id);
}
