package com.vlados.model.service;

import com.vlados.controller.util.PasswordEncoder;
import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.UserDao;
import com.vlados.model.dto.UserDTO;
import com.vlados.model.entity.User;
import com.vlados.model.exception.StoreException;
import com.vlados.model.exception.store_exc.LoginException;
import com.vlados.model.exception.store_exc.login_exc.UserDoesntExist;
import com.vlados.model.exception.store_exc.login_exc.UserIsLockedException;
import com.vlados.model.exception.store_exc.login_exc.WrongPasswordException;
import com.vlados.model.util.ExceptionKeys;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final DaoFactory daoFactory;
    private final PasswordEncoder passwordEncoder;

    public UserService(DaoFactory daoFactory, PasswordEncoder passwordEncoder) {
        this.daoFactory = daoFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public User.Role checkUserAndGetRole(String username, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.findByUsername(username).orElseThrow(()-> {
                //TODO log
                throw new UserDoesntExist(ExceptionKeys.USER_DOESNT_EXIST);
            });
            if (!user.getPassword().equals(passwordEncoder.encode(password))) {
                //TODO log
                System.err.println("wrong password");
                throw new WrongPasswordException(ExceptionKeys.WRONG_PASSWORD);
            }
            if (user.isLocked()) {
                //TODO log
                System.err.println("user is locked");
                throw new UserIsLockedException(ExceptionKeys.USER_LOCKED);
            }
            return user.getRole();
        }
    }

    public void saveUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setRole(User.Role.USER.name());
        userDTO.setLocked(false);

        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(new User(userDTO));
        }
    }

    public List<User> getUsers() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    public User findByUserName(String username) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByUsername(username).get();
            //TODO check optional
        }
    }

    public boolean lockUser(Long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.lockUserById(id);
        } catch (Exception e) {
            System.err.println("Cant lock user");
            //TODO handle exception
        }
        return false;
    }

    public boolean unlockUser(Long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.unlockUserById(id);
        } catch (Exception e) {
            System.err.println("Cant lock user");
            //TODO handle exception
        }
        return false;
    }
}
