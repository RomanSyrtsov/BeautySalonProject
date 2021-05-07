package ua.kharkiv.rsyrtsov.db;

import ua.kharkiv.rsyrtsov.db.model.User;

public enum Role {
    CLIENT("CLIENT"), MASTER("MASTER"), ADMIN("ADMIN");
    private String value;

    Role(String value) {
        this.value = value;
    }

    public static String getRole(User user) {
        int roleId = user.getRoleId();
        return values()[roleId-1].value();
    }

    private String value() {
        return value;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
