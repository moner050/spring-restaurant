package com.my.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface BaseDbRepository<T> {
    // 하나 꺼내오기
    Optional<T> findById(int index);
    // 삽입 or 수정
    T save(T entity);
    // 삭제
    T deleteById(int index);
    // 여러개 꺼내오기
    List<T> findAll();
}
