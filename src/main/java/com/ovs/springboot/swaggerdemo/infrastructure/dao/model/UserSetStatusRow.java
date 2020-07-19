package com.ovs.springboot.swaggerdemo.infrastructure.dao.model;

import com.ovs.springboot.swaggerdemo.model.Data;

import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Optional;

@Table(name = "USER_SET_STATUS")
public class UserSetStatusRow extends Data {
    private final long userId;
    private final ZonedDateTime updatedBy;
    private final ZonedDateTime creationTs;
    private final int identifierseq;
    private final Optional<String> city;
    private final String postcode;

    private UserSetStatusRow(Builder builder) {
        this.userId = builder.userId;
        this.updatedBy = builder.updatedBy;
        this.creationTs = builder.creationTs;
        this.identifierseq = builder.identifierseq;
        this.city = builder.city;
        this.postcode = builder.postcode;

    }

    //getters
    public long userId() {
        return userId;
    }

    public ZonedDateTime updatedBy() {
        return updatedBy;
    }

    public ZonedDateTime creationTs() {
        return creationTs;
    }

    public int identifierseq() { return identifierseq; }

    public Optional<String> city() {
        return city;
    }

    public String postcode() {
        return postcode;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    //inner static class
    public static class Builder {
        private long userId;
        private ZonedDateTime updatedBy;
        private ZonedDateTime creationTs;
        private int identifierseq;
        private Optional<String> city;
        private String postcode;

        //setters
        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder updatedBy(ZonedDateTime updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder creationTs(ZonedDateTime creationTs) {
            this.creationTs = creationTs;
            return this;
        }

        public Builder identifierseq(int identifierseq) {
            this.identifierseq = identifierseq;
            return this;
        }

        public Builder city(Optional<String> city) {
            this.city = city;
            return this;
        }

        public Builder postcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public UserSetStatusRow build() {
            return new UserSetStatusRow(this);
        }

    }

}
