package com.sce.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sce.entity.User;

@Repository
public class UserDao extends BaseDao<User, String> {
    
    private static Logger logger = LoggerFactory.getLogger(UserDao.class);

    public UserDao() {
        super(User.class);
        logger.debug("Instantiating UserDao");
    }

}