package com.ovs.springboot.swaggerdemo.dao;

import com.ovs.springboot.swaggerdemo.model.Location;
import com.ovs.springboot.swaggerdemo.model.UserSet;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    //city version query
    List<Location> getUserSetLocationDetail(long userId);

    Optional<UserSet> getUserSet(long userId);

    default Optional<UserSet> findUserSetById(long userId) {
        Optional<UserSet> userSet = findUserSetById(userId);
        return userSet;
    }

    long addUserSet(UserSet userSet);
}