package com.hjm.dao;

import java.util.List;

public interface IBaseDao<T> {
    T getById(String id);
    T getOne(T t);
    List<T> getList(T t);

    void insert(T t);
    void update(T t);
    void delete(T t);
    void deleteList(List<T> list);
}
