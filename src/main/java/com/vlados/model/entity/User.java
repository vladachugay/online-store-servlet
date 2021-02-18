package com.vlados.model.entity;

import com.vlados.model.dto.UserDTO;

import java.util.Collections;
import java.util.Objects;

public class User {

    private long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private boolean locked;
    private Role role;

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.fullName = userDTO.getFullName();
        this.email = userDTO.getEmail();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.locked = userDTO.isLocked();
        this.role = Role.valueOf(userDTO.getRole());
    }


    public enum Role {
        ADMIN,
        USER,
        GUEST;

        public String getAuthority() {
            return this.name();
        }
    }

    public User(long id, String username, String password, String fullName, String email, String phoneNumber, boolean locked, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.locked = locked;
        this.role = role;
    }

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", locked=" + locked +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                locked == user.locked &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, fullName, email, phoneNumber, locked, role);
    }

    public static class UserBuilder {

        private long id;
        private String username;
        private String password;
        private String fullName;
        private String email;
        private String phoneNumber;
        private boolean locked;
        private Role role;

        public User.UserBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public User.UserBuilder fullName(final String firstName) {
            this.fullName = firstName;
            return this;
        }

        public User.UserBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public User.UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public User.UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public User.UserBuilder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User.UserBuilder role(final User.Role role) {
            this.role = role;
            return this;
        }

        public User.UserBuilder locked(final boolean locked) {
            this.locked = locked;
            return this;
        }

        public User build() {
            return new User(id, username, password, fullName, email, phoneNumber, locked, role);
        }
    }
}
