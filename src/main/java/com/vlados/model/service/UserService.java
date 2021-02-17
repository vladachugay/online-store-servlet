package com.vlados.model.service;

import com.vlados.controller.util.PasswordEncoder;
import com.vlados.model.dao.DaoFactory;
import com.vlados.model.dao.UserDao;
import com.vlados.model.dto.UserDTO;
import com.vlados.model.entity.User;

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
            //TODO use password encoder
            User user = userDao.findByUsername(username).get();
            System.out.println(user);
            //TODO: orElseThrow() - throw exception.
            if (!user.getPassword().equals(passwordEncoder.encode(password))) {
                //TODO throw exception;
                System.err.println("wrong password");
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
            //TODO throw exception
            //TODO log
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
        }
        return false;
    }

    public boolean unlockUser(Long id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.lockUserById(id);
        } catch (Exception e) {
            System.err.println("Cant lock user");
        }
        return false;
    }
}
