package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.SystemOpenTime;
import com.jiang.ssgp.repository.SystemOpenTimeRepository;
import com.jiang.ssgp.service.SystemOpenTimeService;
import org.springframework.stereotype.Service;

@Service
public class SystemOpenTimeServiceImpl implements SystemOpenTimeService {
    private final SystemOpenTimeRepository systemOpenTimeRepository;

    public SystemOpenTimeServiceImpl(SystemOpenTimeRepository systemOpenTimeRepository) {
        this.systemOpenTimeRepository = systemOpenTimeRepository;
    }

    @Override
    public SystemOpenTime save(SystemOpenTime systemOpenTime) {
        return systemOpenTimeRepository.save(systemOpenTime);
    }

    @Override
    public SystemOpenTime findOne(String id) {
        return systemOpenTimeRepository.findById(id).orElse(null);
    }
}
