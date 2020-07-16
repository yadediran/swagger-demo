package com.ovs.springboot.swaggerdemo.dao;

import com.ovs.springboot.swaggerdemo.model.Location;
import com.ovs.springboot.swaggerdemo.model.UserSet;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    static UserSet getLocation() {
        return getLocation();
    }

    static UserSet getUserSet() {
        return getUserSet();
    }

    Optional<Location> getLocation(long userId);


    //city version query
    List<Location> getLocationDetail(long userId);

    Optional<UserSet> getUserSet(long userId);

    Optional<UserSet> findUserSetById(long userId);

    long addUserSet(UserSet userSet);
}