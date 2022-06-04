package com.example.springapp.auth;

import org.springframework.security.core.userdetails.User;

import java.util.Set;


public class AdminUser extends User {
    public AdminUser() {
        super("admin", "password", Set.of());
    }
}
