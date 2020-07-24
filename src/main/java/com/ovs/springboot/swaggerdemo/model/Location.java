package com.ovs.springboot.swaggerdemo.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jdk.nashorn.internal.objects.Global;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.net.InetAddress.getLocalHost;

@JsonDeserialize(builder = Location.LocationBuilder.class)
public class Location extends Data {
    private final long cityId;
    private final long userId;
    private final LocationType locationType;
    private static Global StdOut;
    private final double longitude;
    private final double latitude;
    private final boolean ipAddress;
    private final String users;

    // create and initialize a point with given name and
    // (latitude, longitude) specified in degrees
    //add parameter constructor
    private Location(LocationBuilder builder) {
        this.cityId = LocationBuilder.cityId;
        this.userId = LocationBuilder.userId;
        this.locationType = LocationBuilder.locationType;
        this.longitude = LocationBuilder.longitude;
        this.latitude = LocationBuilder.latitude;
        this.ipAddress = LocationBuilder.ipAddress;
        this.users = LocationBuilder.users;


    }

    public Location(String london, double v, double v1, double name, double longitude, double latitude, String users, long cityId, long userId, LocationType locationType, double longitude1, double latitude1, boolean ipAddress, String users1) {
        super();
        this.cityId = cityId;
        this.userId = userId;
        this.locationType = locationType;
        this.longitude = longitude1;
        this.latitude = latitude1;
        this.ipAddress = ipAddress;
        this.users = users1;
    }


    //provide getters
    public long cityId() {return cityId;}

    public long userId(){return userId;}

    public LocationType locationType() {return locationType;}

    public double longitude(){return longitude;}

    public double latitude(){return latitude;}

    public boolean ipAddress(){return ipAddress;}

    public String users(){return users;}

    public static LocationBuilder newBuilder() {return new LocationBuilder();}

    public LocationBuilder toBuilder(){
        return new LocationBuilder()
                .cityId(cityId)
                .userId(userId)
                .locationType(locationType)
                .longitude(longitude)
                .ipAddress(ipAddress)
                .latitude(latitude);

    }

    public static <K, T> K getlocation(T t) {
        return getlocation(t);
    }


    //inner builder class
    @JsonPOJOBuilder
    public static class LocationBuilder {
        private static double name;
        private static double longitude;
        private static double latitude;
        private static String users;
        private static long cityId;
        private static long userId;
        private static boolean ipAddress;
        private static LocationType locationType;


        public LocationBuilder(){

        }
        @JsonSetter
        public LocationBuilder cityId(long cityId){
            LocationBuilder.cityId = cityId;
            return this;
        }

        @JsonSetter
        public LocationBuilder userId(long userId){
            LocationBuilder.userId = userId;
            return this;
        }

        @JsonSetter
        public LocationBuilder locationType(LocationType locationType){
            LocationBuilder.locationType = locationType;
            return this;
        }



        @JsonSetter
        public LocationBuilder longitude(double longitude) {
            LocationBuilder.longitude = longitude;
            return this;
        }

        @JsonSetter
        public LocationBuilder latitude(double latitude) {
            LocationBuilder.latitude = latitude;
            return this;
        }

        @JsonSetter
        public LocationBuilder users (String users){
            LocationBuilder.users = users;
            return this;
        }
        @JsonSetter
        public LocationBuilder name (double name){
            LocationBuilder.name = name;
            return this;
        }

        @JsonSetter
        public LocationBuilder ipAddress(boolean ipAddress){
            LocationBuilder.ipAddress = ipAddress;
            return this;
        }

        // return string representation of this point
        public String toString() {
            return name + " (" + users +" " + latitude + ", " + longitude + ")";
        }


        // test client
        public static void main(String[] args) throws UnknownHostException {
            double longitude1 = 0;
            double latitude1 = 0;
            String users1 = users;
            Location location = new Location("London", 40.366633, 74.640832, name, longitude, latitude, users, cityId, userId, locationType, longitude1, latitude1, ipAddress, users1);
            Location location1 = new Location("Mylocation", 42.443087, 76.488707, name, longitude, latitude, users, cityId, userId, locationType, longitude1, latitude1, ipAddress, users1);
            double distance = location.distanceTo(location1);
            Global.print("%6.3f miles from\n", distance);
            Global.println(location + " to " + location1);
            InetAddress inetAddress = getLocalHost();
            Global.println("IP Address:-" + inetAddress.getHostAddress());
            Global.println("Host Name:-" + inetAddress.getHostAddress());
        }
        public Location build() {
            return new Location(this);
        }
    }

    // return distance between this location and that location
    // measured in statute miles
    public double distanceTo(Location myLocation) {
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(myLocation.latitude);
        double lon2 = Math.toRadians(myLocation.longitude);

        // get circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }
}