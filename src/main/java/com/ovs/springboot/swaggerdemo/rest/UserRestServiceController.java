package com.ovs.springboot.swaggerdemo.rest;

import com.ovs.springboot.swaggerdemo.model.UserSet;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/users")
@Api(value = "User REST Endpoint" )
public class UserRestServiceController implements UserRestAPI {

    ConcurrentMap<String , UserSet> userSets = new ConcurrentHashMap<>();
    @Override
    @GetMapping("/{userId}")
    @ApiOperation(value = " returns the JSON representation and Finds users by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Suceess|OK"),
                    @ApiResponse(code = 404, message = "not found!!!")
            }
    )
    public UserSet getUserSet(@ApiParam(value = "ID value for the user to be retrieve" , required =true)
                                  @PathVariable String userId){
        return userSets.get(userId);

    }
    @Override
    @GetMapping("/")
    public List <UserSet> getAllUsers(){
        return new ArrayList<UserSet>(userSets.values());


    }
    /*
    @Override
    @PostMapping("/")
    public UserSet addUserSet(@RequestBody UserSet userSet){
        userSets.put(String.valueOf(userSet.userId()), userSet);
        return userSet;

    }
    */

}
