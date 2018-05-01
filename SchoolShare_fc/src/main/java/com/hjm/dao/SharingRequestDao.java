package com.hjm.dao;

import com.hjm.model.SharingRequest;
import com.hjm.model.User;

import java.util.List;

public interface SharingRequestDao {
    void insert(SharingRequest sharingRequest); //在请求表中插入一条新的请求
    void delete(SharingRequest sharingRequest); //删掉某个的请求
    void update(SharingRequest sharingRequest);
    List<SharingRequest> getOwnerList(User owner);
    List<SharingRequest> getRequesterList(User requester);
}
