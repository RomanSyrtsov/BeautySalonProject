package ua.kharkiv.rsyrtsov.controller;

import ua.kharkiv.rsyrtsov.db.Role;
import ua.kharkiv.rsyrtsov.db.model.User;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setLogin("Omar");
        user.setPassword("123");
        user.setEmail("hello@gmail.com");
        user.setRoleId(1);
        System.out.println(Role.getRole(user));
        List<Role> roles = new ArrayList<>();
        roles.add(Role.CLIENT);
        String role = "CLIENT";
        System.out.println(roles.contains(Role.valueOf(role)));


    }
}
