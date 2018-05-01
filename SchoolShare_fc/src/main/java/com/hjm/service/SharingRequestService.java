package com.hjm.service;

import com.hjm.model.SharingRequest;
import com.hjm.model.User;

import java.util.List;

public interface SharingRequestService {
    void insert(SharingRequest sharingRequest); //在请求表中插入一条新的请求
    void delete(SharingRequest sharingRequest); //删掉某个请求
    void update(SharingRequest sharingRequest);
    List<SharingRequest> getOwnerList(User owner);
    List<SharingRequest> getRequesterList(User requester);
}
