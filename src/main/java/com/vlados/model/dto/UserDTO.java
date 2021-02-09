package com.vlados.model.dto;

import com.vlados.model.entity.User;

public class UserDTO {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private boolean locked;
    private String role;

    public UserDTO(final String username, final String password, final String fullName, final String email,
                   final String phoneNumber, final boolean locked, final String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.locked = locked;
        this.role = role;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", locked=" + locked +
                ", role='" + role + '\'' +
                '}';
    }

    public static class UserDTOBuilder {
        private String username;
        private String password;
        private String fullName;
        private String email;
        private String phoneNumber;
        private boolean locked;
        private String role;


        public UserDTO.UserDTOBuilder fullName(final String firstName) {
            this.fullName = firstName;
            return this;
        }

        public UserDTO.UserDTOBuilder username(final String username) {
            this.username = username;
            return this;
        }

        public UserDTO.UserDTOBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UserDTO.UserDTOBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public UserDTO.UserDTOBuilder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDTO.UserDTOBuilder role(final String role) {
            this.role = role;
            return this;
        }

        public UserDTO.UserDTOBuilder locked(final boolean locked) {
            this.locked = locked;
            return this;
        }

        public UserDTO build(){
            return new UserDTO(username, password, fullName, email, phoneNumber, locked, role);
        }

        @Override
        public String toString() {
            return "UserDTOBuilder{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", email='" + email + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", locked=" + locked +
                    ", role='" + role + '\'' +
                    '}';
        }
    }
}
