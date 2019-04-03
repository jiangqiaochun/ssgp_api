package com.jiang.ssgp.service;

import com.jiang.ssgp.domain.po.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jqc
 * @create 2019-03-18 17:07
 */
public interface UserService {
    User findById(String userId);

    User changePsw(String userId, String newPsw);

}