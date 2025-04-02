package com.devesh.bookmyshow.persistence;


import java.util.List;
import java.util.Optional;

public interface IPersistence<T> {
    T save(T entity);
    Optional<T> findById(Long id);
    void deleteById(Long id);
    List<T> findAll();
}


