package com.jt.bookingschedule.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet(ApplicationUserPermission.APPOINTMENT_READ,
            ApplicationUserPermission.CLIENT_READ)),
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
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return  permissions;
    }
}
