package com.hjm.dao;

import com.hjm.model.Good;

public interface GoodPictureDao {
    void delete(String url);
    void deleteAll(Good good);
    void insert(String url,Good good);
}
