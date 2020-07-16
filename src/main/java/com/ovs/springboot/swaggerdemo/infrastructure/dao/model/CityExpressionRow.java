package com.ovs.springboot.swaggerdemo.infrastructure.dao.model;

import javax.persistence.Table;
import java.util.Optional;

@Table(name = "CITY_EXPRESSION")
public class CityExpressionRow {
    private final Long cityExpressionId;


    private CityExpressionRow(CityExpressionRowBuilder cityExpressionRowBuilder) {
        cityExpressionId = cityExpressionRowBuilder.cityExressionId;
    }

    //getters
    public Optional<Long> cityEpressionId() { return Optional.of(cityExpressionId);}

    public static CityExpressionRowBuilder newBuilder() {
        return new CityExpressionRowBuilder();
    }



    /*public CityExpressionRowBuilder copy(){
         return new CityExpressionRowBuilder()
                 .cityExpressionId(cityExpressionId);
     }*/
//inner class
    public static final class CityExpressionRowBuilder {
        public Long cityExressionId;
        private long cityExpressionId;



        public CityExpressionRowBuilder cityEpressionId(long cityExpressionId) {
            this.cityExpressionId = cityExpressionId;
            return this;
        }

        public CityExpressionRow build() {
            return new CityExpressionRow(this);
        }
    }
}

