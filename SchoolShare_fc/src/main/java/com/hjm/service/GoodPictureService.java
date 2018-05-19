package com.hjm.service;

import com.hjm.model.Good;

import java.util.List;

public interface GoodPictureService {
    void delete(String url);
    void deleteAll(Good good);
    void insert(String url,Good good);
    void insertList(Good good);
}
