package com.ovs.springboot.swaggerdemo.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@JsonDeserialize(builder = UserSet.Builder.class)
public class UserSet extends Data {
    private final List<Location> locationDetails;
    private final long userId;
    private final Optional<String> city;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String street;
    private final String postcode;
    private final ZonedDateTime creationTs;
    private final ZonedDateTime updatedBy;


    //add parameter constructor
    public UserSet(Builder builder) {
        this.userId = builder.userId;
        this.city = builder.city;
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.email = builder.email;
        this.street = builder.street;
        this.postcode = builder.postcode;
        this.locationDetails = builder.locationDetails;
        this.creationTs = builder.creationTs;
        this.updatedBy = builder.updatedBy;

    }

    //provide getters
    public long userId() {
        return userId;
    }

    public Optional<String> city() {
        return city;
    }

    public String first_name() {
        return first_name;
    }

    public String last_lame() {
        return last_name;
    }

    public String email() {
        return email;
    }

    public String street() {
        return street;
    }

    public String postcode() {
        return postcode;
    }

    public List<Location> locationDetails() {
        return locationDetails;
    }

    public ZonedDateTime creationTs(){
        return creationTs;
    }

    public ZonedDateTime updatedBy(){
        return updatedBy;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    //inner builder class
    @JsonPOJOBuilder
    public static class Builder {
        private Long userId;
        private Optional<String> city = Optional.empty();
        private String first_name;
        private String last_name;
        private String email;
        private String street;
        private String postcode;
        private List<Location> locationDetails = new ArrayList<>();
        private ZonedDateTime creationTs;
        private ZonedDateTime updatedBy;
        private UserSetStatus currentStatus;

        private Builder(){

        }

        @JsonSetter
        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        @JsonSetter
        public Builder city(Optional<String> city) {
            this.city = city;
            return this;
        }

        @JsonSetter
        public Builder first_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        @JsonSetter
        public Builder last_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        @JsonSetter
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        @JsonSetter
        public Builder street(String street){
            this.street = street;
            return this;
        }
        @JsonSetter
        public Builder postcode(String postcode){
            this.postcode = postcode;
            return this;
        }
        @JsonSetter
        public Builder locationDetails(List<Location> locationDetails){
            this.locationDetails = locationDetails;
            return this;
        }

        @JsonSetter
        public Builder updatedBy (ZonedDateTime updatedBy){
            this.updatedBy = updatedBy;
            return this;
        }

        @JsonSetter
        public Builder creationTs (ZonedDateTime creationTs){
            this.creationTs = creationTs;
            return this;
        }

        @JsonSetter
        public Builder currentStatus(UserSetStatus currentStatus) {
            this.currentStatus = currentStatus;
            return this;
        }
        public UserSet build() {
            return new UserSet(this);}


    }

}
