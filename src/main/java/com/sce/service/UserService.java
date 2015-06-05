package com.sce.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.mapping.Result;
import com.sce.dao.UserAccessor;
import com.sce.dao.UserDao;
import com.sce.entity.Response;
import com.sce.entity.User;
import com.sce.entity.Response.ResultType;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private @Autowired UserDao userDao;

    private @Autowired UserAccessor userAccessor;

    public Response save(User entity) {
        logger.debug("Inside save");
        User user = get(entity.getEmail());
        Response result = null;
        try {
            if (null != user) {
                result = Response.get(ResultType.FAILURE, "User Already Exists");
            } else {
                userDao.save(entity);
                result = Response.get(ResultType.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("Exception : {}", e.getMessage(), e);
            result = Response.get(ResultType.FAILURE, e.getMessage());
        }
        return result;
    }

    public List<User> get() {
        logger.debug("Inside get");
        Result<User> users = userAccessor.getAll();
        List<User> response = new ArrayList<User>();
        for (User user : users) {
            response.add(user);
        }
        return response;
    }

    public User get(String email) {
        logger.debug("Inside getByEmail");
        return userAccessor.getByEmail(email);
    }

}
