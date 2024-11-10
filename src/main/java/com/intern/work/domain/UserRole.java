package com.intern.work.domain;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    UserRole(String role) {
        this.role = role;
    }

    private final String role;

    public String getAuthority() {
        return this.role;
    }

    public static UserRole fromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getAuthority().equalsIgnoreCase(role)) {
                return userRole;
            }
        }

        throw new IllegalArgumentException("No enum constant for role: " + role);
    }
}
