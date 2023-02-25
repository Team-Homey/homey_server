package com.homey.homeyserver.domain;

public enum FamilyRole {
    FAMILY_ROLE_FATHER("FAMILY_ROLE_FATHER"),
    FAMILY_ROLE_MOTHER("FAMILY_ROLE_MOTHER"),
    FAMILY_ROLE_PARENT("FAMILY_ROLE_PARENT"),
    FAMILY_ROLE_GRANDPARENT("FAMILY_ROLE_GRANDPARENT"),
    FAMILY_ROLE_CHILD("FAMILY_ROLE_CHILD"),
    FAMILY_ROLE_DAUGHTER("FAMILY_ROLE_DAUGHTER"),
    FAMILY_ROLE_SON("FAMILY_ROLE_SON");

    String role;

    FamilyRole(String role) {
        this.role = role;
    }


}
