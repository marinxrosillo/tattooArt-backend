package com.tattooart.persistence.enums;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String toString() {
        switch (this) {
            case ROLE_ADMIN:
                return "ROLE_ADMIN";
            case ROLE_USER:
                return "ROLE_USER";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
