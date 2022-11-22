package com.jt.bookingschedule.security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.CLIENT_READ,
            ApplicationUserPermission.CLIENT_WRITE,
            ApplicationUserPermission.APPOINTMENT_WRITE,
            ApplicationUserPermission.APPOINTMENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
