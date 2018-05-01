package com.hjm.service.impl;

import com.hjm.dao.SharingRequestDao;
import com.hjm.model.SharingRequest;
import com.hjm.model.User;
import com.hjm.service.SharingRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SharingRequestServiceImpl implements SharingRequestService {
    @Resource
    private SharingRequestDao sharingRequestDao;

    @Override
    public void insert(SharingRequest sharingRequest) {
        sharingRequestDao.insert(sharingRequest);
    }

    @Override
    public void delete(SharingRequest sharingRequest) {
        sharingRequestDao.delete(sharingRequest);
    }

    @Override
    public void update(SharingRequest sharingRequest) {
        sharingRequestDao.update(sharingRequest);
    }

    @Override
    public List<SharingRequest> getOwnerList(User owner) {
        return sharingRequestDao.getOwnerList(owner);
    }

    @Override
    public List<SharingRequest> getRequesterList(User requester) {
        return sharingRequestDao.getRequesterList(requester);
    }
}
