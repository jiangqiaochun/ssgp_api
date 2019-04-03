package com.jiang.ssgp.service.impl;

import com.jiang.ssgp.domain.po.User;
import com.jiang.ssgp.repository.UserRepository;
import com.jiang.ssgp.service.UserService;
import com.jiang.ssgp.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jqc
 * @create 2019-03-18 17:08
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public UserServiceImpl(UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User findById(String userId){
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User changePsw(String userId, String newPsw) {
        Criteria criteria = Criteria.where("_id").is(userId);
        Update update = new Update();
        update.set("password", newPsw);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        return mongoTemplate.findAndModify(new Query(criteria), update, options, User.class);
    }

}