package com.sce.dao;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.sce.entity.User;

@Accessor
public interface UserAccessor {
    @Query("SELECT * FROM user WHERE email = :email")
    User getByEmail(@Param("email") String email);

    @Query("SELECT * FROM user")
    public Result<User> getAll();

}