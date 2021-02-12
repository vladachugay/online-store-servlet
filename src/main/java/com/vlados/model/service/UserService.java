package com.vlados.model.service;

import com.vlados.controller.util.PasswordEncoder;
import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.UserDao;
import com.vlados.model.entity.User;

public class UserService {
    private final DaoFactory daoFactory;
    private final PasswordEncoder passwordEncoder;

    public UserService(DaoFactory daoFactory, PasswordEncoder passwordEncoder) {
        this.daoFactory = daoFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public User.Role checkUserAndGetRole(String username, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            //TODO use password encoder
            User user = userDao.findByUsername(username).get();
            System.out.println(user);
            //TODO: orElseThrow() - throw exception.
            if (!user.getPassword().equals(password)) {
                //TODO throw exception;
            }
            return user.getRole();
        }
    }
}
