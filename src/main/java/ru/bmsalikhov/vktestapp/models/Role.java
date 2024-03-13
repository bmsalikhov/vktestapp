package ru.bmsalikhov.vktestapp.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USERS, ROLE_POSTS, ROLE_ALBUMS;

    @Override
    public String getAuthority() {
        return name();
    }
}