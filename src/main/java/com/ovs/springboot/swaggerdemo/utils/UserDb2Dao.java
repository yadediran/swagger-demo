package com.ovs.springboot.swaggerdemo.utils;

import com.cadenzauk.core.lang.CompositeAutoCloseable;
import com.cadenzauk.core.tuple.Tuple4;
import com.cadenzauk.siesta.Database;
import com.ovs.springboot.swaggerdemo.dao.UserDao;

import com.ovs.springboot.swaggerdemo.infrastructure.dao.model.*;
import com.ovs.springboot.swaggerdemo.model.Location;
import com.ovs.springboot.swaggerdemo.model.LocationType;
import com.ovs.springboot.swaggerdemo.model.UserSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.cadenzauk.siesta.Order.DESC;
import static java.util.stream.Collectors.groupingBy;





public class UserDb2Dao implements UserDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDb2Dao.class);
    private static final int LOCATION_THRESHOLD = 50;
    //a regex for a digit zero to 255 and followed by a dot, repeat 4 times - this is the regex to validate an IP Address
    private static final String ipAddress = "(\\d{1,2}|(0|1)\\" + "d{2} | 2[0-4]\\d|25[0-5])";
    private static String regex = ipAddress + "\\." + ipAddress + "\\." + ipAddress + "\\." + ipAddress;
    private static Pattern pattern = Pattern.compile(regex);
    private static Matcher matcher = pattern.matcher(ipAddress);
    private final Database database;
    //private final UserRepository userRepository;


    public UserDb2Dao(Database database) {
        this.database = database;
        //this.userRepository = userRepository;


    }

    @Override
    public Optional<Location> getLocation(long userId) {
        return Optional.empty();
    }

    @Override
    //city version query
    public List<Location> getUserSetLocationDetail(long userId) {
        List<CityVersionRow> latestVersionCityVersionRows = new ArrayList<>();
        try (CompositeAutoCloseable closer = new CompositeAutoCloseable()) {
            database.from(CityRow.class, "c")
                    .join(CityExpressionRow.class, "ce")
                    .on(CityExpressionRow::cityEpressionId).isEqualTo(CityRow::cityExpressionId)
                    .join(CitySetRow.class, "cs")
                    .on(CitySetRow::citysetId).isEqualTo(CityRow::citysetId)
                    .join(CityVersionRow.class, "cv")
                    .on(CityVersionRow::cityId).isEqualTo(CityRow::cityId)
                    .where("cv", CityVersionRow::identifierseq).isEqualTo(
                    database.from(CityVersionRow.class, "cv")
                            .select("cv", CityVersionRow::identifierseq)
                            .where("cv", CityVersionRow::cityId).isEqualTo("cv", CityVersionRow::cityId)
                            .orderBy("cv", CityVersionRow::identifierseq, DESC)
                            .fetchFirst(1))
                    .and("cv", CityVersionRow::userId).isEqualTo(userId)
                    .stream(closer)
                    .map(Tuple4::item4)
                    .forEach(latestVersionCityVersionRows::add);


        }

        List<CityVersionRow> last2CityVersionRows = new ArrayList<>(latestVersionCityVersionRows);
        latestVersionCityVersionRows.forEach(cv -> {
            try (CompositeAutoCloseable closer = new CompositeAutoCloseable()) {
                database.from(CityVersionRow.class)
                        .where(CityVersionRow::cityId).isEqualTo(cv.cityId())
                        .and(CityVersionRow::identifierseq).isEqualTo(cv.identifierseq() - 1)
                        .stream(closer)
                        .forEach(last2CityVersionRows::add);
            }
        });
        return buildUserDetail(last2CityVersionRows);

    }

    @Override
    //userset query
    public Optional<UserSet> getUserSet(long userId) {
        //return a list of location and call getUserSetLocationDetail
        List<Location> locations = getUserSetLocationDetail(userId);
        return database.from(UserSetRow.class, "us")
                .join(UserSetStatusRow.class, "uss")
                .on(UserSetRow::userId).isEqualTo(UserSetRow::userId)
                .where("uss", UserSetStatusRow::identifierseq).isEqualTo(
                        database.from(UserSetStatusRow.class, "uss")
                                .select("uss", UserSetStatusRow::identifierseq)
                                .where("uss", UserSetStatusRow::userId).isEqualTo(userId)
                                .orderBy("uss", UserSetStatusRow::identifierseq, DESC)
                                .fetchFirst(1))
                .and(UserSetRow::userId).isEqualTo(userId)
                .optional()
                .map(name -> UserSet.getBuilder()
                        .locationDetails(locations)
                        .userId(name.item1().userId())
                        .first_name(name.item1().first_name())
                        .last_name(name.item1().last_name())
                        .postcode(name.item1().postcode())
                        .email(name.item1().email())
                        .updatedBy(name.item2().updatedBy())
                        .creationTs(name.item2().creationTs())
                        .build());
    }


    private List<Location> buildUserDetail(List<CityVersionRow> cityVersionRows) {
        // Accumulate names of people (users) into a List
        List<Location> users = new ArrayList<>();

        //Accumulate rows of people into a list
        Map<Long, List<CityVersionRow>> groupData = cityVersionRows.stream().collect(groupingBy(CityVersionRow::userId));

        //group users by Location
        Map<UserSet, List<Location>> byLocation
                = users.stream()
                .collect(Collectors.groupingBy(Location::getlocation));


        // Partition People into London and 50miles using a Stream API chain rather than a for loop
        Map<Boolean, List<Location>> locationType =
                users.stream()
                        .collect(Collectors.partitioningBy(s -> s.cityId() >= LOCATION_THRESHOLD));

        groupData.keySet().forEach(cityId -> {
            List<CityVersionRow> cityVersions = groupData.get(cityId);

            if (cityVersions.size() > 1) {
                cityVersions.sort(Comparator.comparing(CityVersionRow::identifierseq).reversed());
                users.add(Location.newBuilder()
                        .cityId(cityId)
                        .longitude(cityVersions.get(0).longitude())
                        .latitude(cityVersions.get(0).latitude())
                        .ipAddress(UserDb2Dao.ipAddress())
                        .locationType(LocationType.URBAN)
                        .build());
            } else {
                users.add(Location.newBuilder()
                        .cityId(cityId)
                        .longitude(cityVersions.get(0).longitude())
                        .latitude(cityVersions.get(0).latitude())
                        .ipAddress(UserDb2Dao.ipAddress())
                        .locationType(LocationType.RURAL)
                        .build());

            }
        });
        return users;
    }

    private static boolean ipAddress() {
        return matcher.matches();
    }

    @Override
    public Optional<UserSet> findUserSetById(long userId) {
        return database.from(UserSetRow.class, "usr")
                .where(UserSetRow::userId).isEqualTo(userId)
                .optional()
                .map(userSetRow -> UserSet.getBuilder()
                        .userId(userSetRow.userId())
                        .city(userSetRow.city())
                        .postcode(userSetRow.postcode())
                        .build());
    }

    @Override
    public long addUserSet(UserSet userSet) {
        UserSetRow userSetRow = userSetRow(userSet);
        database.insert(userSetRow);
        addUserSetStatus(userSetRow.userId(), userSet.city(), userSet.postcode());
        return userSetRow.userId();
    }

    private UserSetRow userSetRow(UserSet userSet) {
        return UserSetRow.getBuilder()
                .userId(userSet.userId())
                .city(userSet.city())
                .postcode(userSet.postcode())
                .build();
    }

    private void addUserSetStatus(long userId, Optional<String> city, String postcode) {
        database.insert(userSetStatusRow(userId, city, postcode));
    }

    private UserSetStatusRow userSetStatusRow(long userId, Optional<String> city, String postcode) {
        Optional<UserSetStatusRow> latestUserSetStatusRow = getLastestUserSetStatusRow(userId);

        int identiferseq = latestUserSetStatusRow.map(UserSetStatusRow::identifierseq).map(v -> v + 1).orElse(1);

        return UserSetStatusRow.getBuilder()
                .userId(userId)
                .city(city)
                .postcode(postcode)
                .identifierseq(identiferseq)
                .build();
    }

    private Optional<UserSetStatusRow> getLastestUserSetStatusRow(long userId) {
        return database.from(UserSetStatusRow.class, "uss")
                .where(UserSetStatusRow::userId).isEqualTo(userId)
                .orderBy(UserSetStatusRow::identifierseq, DESC)
                //.fetchFirst(1)
                .optional();

    }

}






