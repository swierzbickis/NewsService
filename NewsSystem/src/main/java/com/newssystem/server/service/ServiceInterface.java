package com.newssystem.server.service;

import java.util.List;

/**
 * Created by Sebek on 2016-10-08.
 */
public interface ServiceInterface<T> {

    List<T> getObj();
    T create(T obj);
    T findById(String id);
    T update(T obj);
}
