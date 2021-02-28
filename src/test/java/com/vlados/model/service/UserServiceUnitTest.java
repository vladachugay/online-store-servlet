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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

    private static final String USERNAME = "username";
    private static final String WRONG_USERNAME = "wrong username";
    private static final long ID = 1;
    private static final String PASSWORD = "password";
    private static final String ENCODED_PASSWORD = "encoded password";
    private static final String WRONG_PASSWORD = "wrong password";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private DaoFactory daoFactory;
    @Mock
    private UserDao userDao;
    @Mock
    private User user;
    @Mock
    private UserDTO userDTO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService testInstance;


    @Before
    public void setUp() {
        when(daoFactory.createUserDao()).thenReturn(userDao);
    }

    @Test
    public void shouldReturnUserByUsername() {
        Optional<User> optionalUser = Optional.of(user);
        when(userDao.findByUsername(USERNAME)).thenReturn(optionalUser);

        User result = testInstance.findByUserName(USERNAME);

        assertEquals(user, result);
    }

    @Test
    public void shouldThrowUserDoesntExist() {
        Optional<User> optionalUser = Optional.empty();
        when(userDao.findByUsername(WRONG_USERNAME)).thenReturn(optionalUser);
        thrown.expect(UserDoesntExist.class);

        testInstance.findByUserName(WRONG_USERNAME);
    }

    @Test
    public void shouldLockUserById() {
        when(userDao.lockUserById(ID)).thenReturn(true);

        boolean result = testInstance.lockUser(ID);

        assertTrue(result);
    }

    @Test
    public void shouldUnlockUserById() {
        when(userDao.unlockUserById(ID)).thenReturn(true);

        boolean result = testInstance.unlockUser(ID);

        assertTrue(result);
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        when(userDao.findAll()).thenReturn(users);

        List<User> result = testInstance.getUsers();

        assertEquals(users, result);
    }

    @Test
    public void shouldSaveNewUser() {
        when(userDao.create(any(User.class))).thenReturn(true);
        when(userDTO.getPassword()).thenReturn(PASSWORD);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(userDTO.getRole()).thenReturn(User.Role.USER.name());

        boolean result = testInstance.saveUser(userDTO);

        assertTrue(result);
        verify(userDTO).setPassword(ENCODED_PASSWORD);
        verify(userDTO).setLocked(false);
        verify(userDTO).setRole(User.Role.USER.name());
    }

    @Test
    public void shouldThrowDuplicateUsernameException() {
        when(userDao.create(any(User.class))).thenThrow(RuntimeException.class);
        when(userDTO.getPassword()).thenReturn(PASSWORD);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(userDTO.getRole()).thenReturn(User.Role.USER.name());
        thrown.expect(DuplicateUsernameException.class);
        thrown.expectMessage(ExceptionKeys.DUPLICATE_USERNAME);

        testInstance.saveUser(userDTO);
    }

    @Test
    public void shouldReturnUserRole() {
        Optional<User> userOptional = Optional.of(user);
        User.Role role = User.Role.USER;
        when(userDao.findByUsername(USERNAME)).thenReturn(userOptional);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(user.getPassword()).thenReturn(ENCODED_PASSWORD);
        when(user.isLocked()).thenReturn(false);
        when(user.getRole()).thenReturn(role);

        User.Role result = testInstance.checkUserAndGetRole(USERNAME, PASSWORD);

        assertEquals(role, result);
    }

    @Test
    public void shouldThrowWrongPasswordException() {
        Optional<User> userOptional = Optional.of(user);

        when(userDao.findByUsername(USERNAME)).thenReturn(userOptional);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(user.getPassword()).thenReturn(WRONG_PASSWORD);

        thrown.expect(WrongPasswordException.class);
        thrown.expectMessage(ExceptionKeys.WRONG_PASSWORD);

        testInstance.checkUserAndGetRole(USERNAME, PASSWORD);
    }

    @Test
    public void shouldThrowUserIsLockedException() {
        Optional<User> userOptional = Optional.of(user);
        when(userDao.findByUsername(USERNAME)).thenReturn(userOptional);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(user.getPassword()).thenReturn(ENCODED_PASSWORD);
        when(user.isLocked()).thenReturn(true);

        thrown.expect(UserIsLockedException.class);
        thrown.expectMessage(ExceptionKeys.USER_LOCKED);

        testInstance.checkUserAndGetRole(USERNAME, PASSWORD);
    }
}
