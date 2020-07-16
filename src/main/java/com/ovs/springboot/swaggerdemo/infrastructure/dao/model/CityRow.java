package com.ovs.springboot.swaggerdemo.infrastructure.dao.model;

import com.ovs.springboot.swaggerdemo.model.Data;

import javax.persistence.Table;


@Table(name = "CITY")
public class CityRow extends Data {
    private final long cityId;
    private final long cityExpressionId;
    private final long citysetId;

    private CityRow(CityRowBuilder cityRowBuilder) {
        this.cityId = cityRowBuilder.cityId;
        this.cityExpressionId = cityRowBuilder.cityExpressionId;
        this.citysetId = cityRowBuilder.citysetId;
    }

    //getters
    public long cityId() { return cityId; }

    public long cityExpressionId() {
        return cityExpressionId;
    }


    public long citysetId() {
        return citysetId;
    }


    public static CityRowBuilder newBuilder() {
        return new CityRowBuilder();
    }

    public CityRowBuilder copy() {
        return new CityRowBuilder()
                .cityId(cityId)
                .cityExpressionId(cityExpressionId)
                .citysetId(citysetId);

    }

    //inner CityRowBuilder class
    public static final class CityRowBuilder {
        private long cityId;
        private long cityExpressionId;
        private long citysetId;

        private CityRowBuilder() {
        }

        public CityRowBuilder cityId(long cityId) {
            this.cityId = cityId;
            return this;
        }

        public CityRowBuilder cityExpressionId(long cityExpressionId) {
            this.cityExpressionId = cityExpressionId;
            return this;
        }


        public CityRowBuilder citysetId(long citysetId) {
            this.citysetId = citysetId;
            return this;

        }

        public CityRow build() { return new CityRow(this); }


    }
}


