package com.vlados.model.service;

import com.vlados.controller.util.PasswordEncoder;
import com.vlados.model.dao.DaoFactory;

public class UserService {
    private final DaoFactory daoFactory;
    private final PasswordEncoder passwordEncoder;

    public UserService(DaoFactory daoFactory, PasswordEncoder passwordEncoder) {
        this.daoFactory = daoFactory;
        this.passwordEncoder = passwordEncoder;
    }
}
