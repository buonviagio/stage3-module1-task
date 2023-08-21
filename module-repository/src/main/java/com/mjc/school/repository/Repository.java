package com.mjc.school.repository;

import java.util.List;

public interface Repository <T> {
    List<T> readAllNews();

    T readById(Long id);

    T create(T t);

    T update(T t);

    Boolean deleteById(Long id);
}
