package com.sce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.entity.Response;
import com.sce.entity.Response.ResultType;

@RestController
public class WelcomeController {

    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping("/")
    public Response hello() {
        logger.debug("Entering hello");
        return Response.get(ResultType.SUCCESS, "Hello!! Welcome to Spring Cassandra Demo");
    }

}
