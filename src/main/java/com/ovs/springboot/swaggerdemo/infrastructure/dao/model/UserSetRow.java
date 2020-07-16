package com.ovs.springboot.swaggerdemo.infrastructure.dao.model;

import com.ovs.springboot.swaggerdemo.model.Data;
import com.ovs.springboot.swaggerdemo.model.UserSetStatus;

import javax.persistence.Table;
import java.util.Optional;

@Table(name = "USER_SET" )
public class UserSetRow extends Data {
    private final long userId;
    private final String first_name;
    private final String last_name;
    private final String postcode;
    private final String email;
    private final String updatedBy;
    private final UserSetStatus currentStatus;
    private final String city;

    private UserSetRow(Builder builder){
        this.userId = builder.userId;
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.postcode = builder.postcode;
        this.email = builder.email;
        this.updatedBy = builder.updatedBy;
        this.currentStatus = builder.currentStatus;
        this.city = builder.city;
    }

    //getters
    public long userId() {return userId;}

    public String first_name(){return first_name;}

    public String last_name(){return last_name;}

    public String postcode(){return postcode;}

    public String email (){return email;}

    public String updatedBy() {return updatedBy; }

    public UserSetStatus currentStatus() {return currentStatus;}

    public Optional<String> city(){return Optional.ofNullable(city);}

    public static Builder getBuilder(){return new Builder(); }

    //inner builder class
    public static class Builder{
        private long userId;
        private String updatedBy;
        private UserSetStatus currentStatus;
        private String first_name;
        private String last_name;
        private String postcode;
        private String email;
        private String city;

        private Builder(){
        }

        //setters
        public Builder userId(long userId){
            this.userId = userId;
            return this;
        }

        public Builder updatedBy(String updatedBy){
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder currentStatus(UserSetStatus currentStatus){
            this.currentStatus = currentStatus;
            return this;
        }

        public Builder first_name(String first_name){
            this.first_name = first_name;
            return this;
        }

        public Builder last_name(String last_name){
            this.last_name = last_name;
            return this;
        }

        public Builder postcode(String postcode){
            this.postcode = postcode;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder city(Optional<String> city) {
            this.city = email;
            return this;
        }

        public UserSetRow build(){return new UserSetRow(this);}


    }

}

