package com.vlados.model.entity;

public class User {

    private long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private boolean locked;
    private Role role;


    public enum Role {
        ADMIN,
        USER,
        UNKNOWN;

        public String getAuthority() {
            return this.name();
        }
    }
}
