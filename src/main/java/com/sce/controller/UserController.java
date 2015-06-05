package com.sce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sce.entity.Response;
import com.sce.entity.User;
import com.sce.service.UserService;

@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private @Autowired UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> users() {
        logger.debug("Inside users");
        return userService.get();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User get(@RequestParam("email") String email) {
        logger.debug("Inside get");
        return userService.get(email);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Response create(@RequestBody User user) {
        logger.debug("Inside create");
        return userService.save(user);
    }

}
