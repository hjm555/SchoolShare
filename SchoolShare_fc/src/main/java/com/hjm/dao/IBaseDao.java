package com.hjm.dao;

import java.util.List;

public interface IBaseDao<T> {
    T getById(String id);          //主键查询
    T getOne(T t);                 //多重条件查询，从数据库相关表中找出和t中相关数据完全一致的一条记录，并把记录重新封装成一个T对象返回
    List<T> getList(T t);          //多重条件查询，从数据库相关表中找出和t中相关数据完全一致的所有记录，并把所有记录封装成List<T>返回

    void insert(T t);              //把t的相关数据一一对应插进数据库相关表
    void update(T t);              //把数据库相关表的记录更新
    void delete(T t);              //多重条件删除，从数据库相关表中删除和t中相关数据完全一致的一条记录
    void deleteList(List<T> list); //多重条件删除，从数据库相关表中删除和t中相关数据完全一致的所有记录
}
