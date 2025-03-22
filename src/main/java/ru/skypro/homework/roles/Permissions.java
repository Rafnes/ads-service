package ru.skypro.homework.roles;

public interface Permissions {
    String ADMIN = "hasRole('ADMIN')";
    String USER = "hasRole('USER')";
    String ALL = "permitAll()";
}