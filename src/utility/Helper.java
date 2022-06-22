package utility;

import DBQuery.UserQuery;

public class Helper {

    public static boolean checkPassword(String username, String password) {

        if (UserQuery.getPassword(username).equals(password)) {
            return true;
        } else {
            return false;
        }

    }



}
