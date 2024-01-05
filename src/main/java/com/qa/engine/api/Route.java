package com.qa.engine.api;

public class Route {
    private static final String APIBASICAUTH = "auth";
    private static final String BOOKINGLIST = "booking";

    private static final String ENDPOINTBUG = "https://gomathijiraintegration.atlassian.net/rest/api/2/issue/";

    public static String generateToken() {
        return APIBASICAUTH;
    }

    public static String booking() {
        return BOOKINGLIST;
    }

    public static String EndPointBug() {
        return ENDPOINTBUG;
    }
}
