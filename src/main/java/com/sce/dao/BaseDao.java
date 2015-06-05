package com.sce.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

public abstract class BaseDao<T, PK extends Serializable> {

    private static Logger logger = LoggerFactory.getLogger(BaseDao.class);

    private @Autowired Session session;

    private @Autowired MappingManager mappingManager;

    protected Class<T> persistentClass;

    private Mapper<T> mapper;

    public BaseDao(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @PostConstruct
    public void setMapper() {
        this.mapper = mappingManager.mapper(this.persistentClass);
    }

    public void save(T entity) {
        logger.debug("Inside Save");
        mapper.save(entity);
    }

    public void get(PK id) {
        logger.debug("Inside get");
        mapper.get(id);
    }

    public void delete(PK id) {
        logger.debug("Inside delete");
        mapper.delete(id);
    }

}
