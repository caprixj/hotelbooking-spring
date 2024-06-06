package com.ips21.hotelbooking.constants;

public final class Constants {

    private Constants() {
        // Private constructor to prevent instantiation
    }

    public static final class AuthErrorMessages {
        public static final String USER_ALREADY_EXISTS = "User already exists.";
        public static final String USER_NOT_FOUND = "User has not been found.";
        public static final String BAD_CREDENTIALS = "Bad credentials (possibly invalid password).";
        public static final String INVALID_EMAIL = "Invalid email.";
        public static final String INVALID_PASSWORD = "Invalid password.";
    }

    public static final class BookingErrorMessages {
        public static final String USER_NOT_FOUND = "User was not found in database while booking.";
        public static final String ROOM_NOT_FOUND = "Room was not found in database while booking.";
        public static final String ROOM_BOOKED_TWICE = "The room is already booked by this user.";
        public static final String ROOM_NOT_FREE = "The room is already booked by another user.";

    }

    public static final class BookingMessages {
        public static final String BOOKED_SUCCESS = "The room has been booked successfully.";
    }
}
