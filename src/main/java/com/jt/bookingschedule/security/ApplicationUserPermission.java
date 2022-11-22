package com.jt.bookingschedule.security;

public enum ApplicationUserPermission {

    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    APPOINTMENT_READ("appointment:read"),
    APPOINTMENT_WRITE("appointment:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
