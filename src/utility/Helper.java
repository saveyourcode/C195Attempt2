package utility;

import DBQuery.UserQuery;

import java.time.*;

public class Helper {

    public static ZoneId zoneUTC = ZoneId.of("UTC");
    public static ZoneId zoneEST = ZoneId.of("America/New_York");
    public static ZoneId zoneDefault = ZoneId.systemDefault();

    /** Returns a boolean value depending on if the username and password matches.
     *
     * @param username String username
     * @param password String password
     * @return True if username and password match in the database and false if they do not
     */
    public static boolean checkPassword(String username, String password) {

        if (UserQuery.getPassword(username).equals(password)) {
            return true;
        } else {
            return false;
        }

    }

    /** Returns a boolean value depending on if the appointment parameter is inside of business hours.*/
    /**
     *
     * @param localDateTime the time to be checked if it's within business hours
     * @return True if time is within business hours and false if it's not
     */
    public static boolean checkIfWithinBusinessHours(LocalDateTime localDateTime) {

        LocalTime openingTime = LocalTime.of(8, 00);
        LocalTime closingTime = LocalTime.of(22, 00);

        ZonedDateTime zonedLocal = ZonedDateTime.of(localDateTime, zoneDefault);

        ZonedDateTime localInEST = ZonedDateTime.ofInstant(zonedLocal.toInstant(), zoneEST);

        if (((localInEST.toLocalTime().isAfter(openingTime)) || (localInEST.toLocalTime().equals(openingTime))) &&
                ((localInEST.toLocalTime().isBefore(closingTime)) || (localInEST.toLocalTime().equals(closingTime)))) {

            return true;
        }

        return false;

    }



}
