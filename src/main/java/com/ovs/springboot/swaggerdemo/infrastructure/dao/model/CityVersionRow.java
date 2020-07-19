package com.ovs.springboot.swaggerdemo.infrastructure.dao.model;

import com.ovs.springboot.swaggerdemo.model.Data;

import javax.persistence.Table;

@Table(name = "CITY_VERSION")
public class CityVersionRow extends Data {
    private final long cityVersionId;
    private final long cityId;
    private final long userId;
    private final int identifierseq;
    private final double longitude;
    private final double latitude;
    //private final String ipAddress;

    public CityVersionRow(CityVersionRowBuilder builder){
        this.cityVersionId = builder.cityVersionId;
        this.cityId = builder.cityId;
        this.userId = builder.userId;
        this.identifierseq = builder.identifierseq;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        //this.ipAddress = builder.ipAddress;
    }

    //getters
    public long cityVersionId() {return cityVersionId;}

    public long cityId() {return cityId;}

    public long userId() {return userId;}

    public int identifierseq() {return identifierseq;}

    public double longitude () {return longitude;}

    public double latitude () {return latitude;}

    //public String ipAddress() {return ipAddress;}

    public static CityVersionRowBuilder newBuilder() {return new CityVersionRowBuilder();}

    public CityVersionRowBuilder copy(){
        return new CityVersionRowBuilder()
                .cityId(cityId)
                .identifierseq(identifierseq)
                .userId(userId)
                .longitude(longitude)
                .latitude(latitude)
                //.ipAddress(ipAddress)
                .cityVersionId(cityVersionId);
    }

    //inner class
    public static final class CityVersionRowBuilder{
        private long cityId;
        private long cityVersionId;
        private long userId;
        private int identifierseq;
        private double longitude;
        private double latitude;
        // private String ipAddress;

        private CityVersionRowBuilder(){}

        //setters

        public CityVersionRowBuilder userId (long userId){
            this.userId = userId;
            return this;
        }

        public CityVersionRowBuilder cityId (long cityId){
            this.cityId = cityId;
            return this;
        }

        public CityVersionRowBuilder cityVersionId(long cityVersionId){
            this.cityVersionId = cityVersionId;
            return this;
        }

        public CityVersionRowBuilder identifierseq(int identifierseq){
            this.identifierseq = identifierseq;
            return this;
        }

        public CityVersionRowBuilder longitude(double longitude){
            this.longitude = longitude;
            return this;
        }

        public CityVersionRowBuilder latitude(double latitude){
            this.latitude = latitude;
            return this;
        }



       /* public CityVersionRowBuilder ipAddress(String ipAddress){
            this.ipAddress = ipAddress;
            return this;
        }*/

        public CityVersionRow build() {return new CityVersionRow(this);}
    }


}
