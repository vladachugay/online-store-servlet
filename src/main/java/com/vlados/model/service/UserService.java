package com.vlados.model.service;

import com.vlados.controller.util.PasswordEncoder;
import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.UserDao;
import com.vlados.model.dto.UserDTO;
import com.vlados.model.entity.User;
import com.vlados.model.exception.store_exc.DuplicateUsernameException;
import com.vlados.model.exception.store_exc.login_exc.UserDoesntExist;
import com.vlados.model.exception.store_exc.login_exc.UserIsLockedException;
import com.vlados.model.exception.store_exc.login_exc.WrongPasswordException;
import com.vlados.model.util.ExceptionKeys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService {
    private final DaoFactory daoFactory;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LogManager.getLogger(UserService.class);

    public UserService(DaoFactory daoFactory, PasswordEncoder passwordEncoder) {
        this.daoFactory = daoFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public User.Role checkUserAndGetRole(String username, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            User user = userDao.findByUsername(username).orElseThrow(()-> {
                logger.error("User with username {} doesnt exits", username);
                throw new UserDoesntExist(ExceptionKeys.USER_DOESNT_EXIST);
            });
            if (!user.getPassword().equals(passwordEncoder.encode(password))) {
                logger.error("Wrong password");
                throw new WrongPasswordException(ExceptionKeys.WRONG_PASSWORD);
            }
            if (user.isLocked()) {
                logger.error("{} is locked", username);
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
        } catch (Exception e) {
            logger.error("Duplicate username ({})", userDTO.getUsername());
            throw new DuplicateUsernameException(ExceptionKeys.DUPLICATE_USERNAME);
        }
    }

    public List<User> getUsers() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    public User findByUserName(String username) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByUsername(username).orElseThrow(()-> {
                logger.error("User with username {} doesnt exits", username);
                throw new UserDoesntExist(ExceptionKeys.USER_DOESNT_EXIST);
            });
        }
    }

    public boolean lockUser(Long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.lockUserById(id);
        }
    }

    public boolean unlockUser(Long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.unlockUserById(id);
        }
    }
}
