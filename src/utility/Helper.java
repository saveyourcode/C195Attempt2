package utility;

import DBQuery.UserQuery;

import java.time.*;

public class Helper {

    public static ZoneId zoneUTC = ZoneId.of("UTC");
    public static ZoneId zoneEST = ZoneId.of("America/New_York");
    public static ZoneId zoneDefault = ZoneId.systemDefault();

    public static boolean checkPassword(String username, String password) {

        if (UserQuery.getPassword(username).equals(password)) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean checkIfWithinBusinessHours(LocalDateTime localDateTime) {

        LocalDate localDate = localDateTime.toLocalDate();

        ZonedDateTime localDefault = ZonedDateTime.of(localDateTime, zoneDefault);
        ZonedDateTime eastOpenDefault = ZonedDateTime.of(localDate, LocalTime.of(8, 00), zoneDefault);
        ZonedDateTime eastCloseDefault = ZonedDateTime.of(localDate, LocalTime.of(22, 00), zoneDefault);

        ZonedDateTime localUTC = ZonedDateTime.ofInstant(localDefault.toInstant(), zoneUTC);
        ZonedDateTime eastOpenUTC = ZonedDateTime.ofInstant(eastOpenDefault.toInstant(), zoneUTC);
        ZonedDateTime eastCloseUTC = ZonedDateTime.ofInstant(eastCloseDefault.toInstant(), zoneUTC);

        ZonedDateTime localEast = ZonedDateTime.ofInstant(localUTC.toInstant(), zoneEST);
        ZonedDateTime eastOpenEST = ZonedDateTime.ofInstant(eastOpenUTC.toInstant(), zoneEST);
        ZonedDateTime eastCloseEST = ZonedDateTime.ofInstant(eastCloseUTC.toInstant(), zoneEST);

        if (((localEast.isAfter(eastOpenEST)) || (localEast.isEqual(eastOpenEST))) &&
                ((localEast.isBefore(eastCloseEST)) || (localEast.isEqual(eastCloseEST)))) {

            return true;
        }


        return false;

    }



}
