package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.SystemOpenTime;

public interface SystemOpenTimeService {
    SystemOpenTime save(SystemOpenTime systemOpenTime);

    SystemOpenTime findOne(String id);
}
