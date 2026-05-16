package com.fredo.book_travel.security.rolseAndPermissions;

import java.util.Set;

public enum Role {
    USER(Set.of(Permission.USER_CREATE, Permission.USER_DELETE, Permission.BOOKING_CREATE, Permission.BOOKING_DELETE,
             Permission.BOOKING_UPDATE)),

    ADMIN(Set.of(Permission.USER_CREATE, Permission.ADMIN_CREATE, Permission.ADMIN_DELETE,
            Permission.ADMIN_UPDATE, Permission.BACKEND_SETTINGS, Permission.USER_DELETE));

   private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

}
