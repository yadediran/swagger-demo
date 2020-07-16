package com.ovs.springboot.swaggerdemo.rest;

import com.ovs.springboot.swaggerdemo.model.UserSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserRestAPI {
    @GetMapping("/{userId}")
    @ApiOperation(value = "Finds users by Id",
            notes = "Provide an id to look up a specific user in a location",
            response = UserSet.class)
    UserSet getUserSet(@ApiParam(value = "ID value for the user to be retrieve", required = true)
                       @PathVariable String userId);

    @GetMapping("/")
    List<UserSet> getAllUsers();
/*
    @PostMapping("/")
    UserSet addUserSet(@RequestBody UserSet userSet);
    */

}
