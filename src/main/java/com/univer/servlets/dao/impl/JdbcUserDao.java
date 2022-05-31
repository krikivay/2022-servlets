package com.univer.servlets.dao.impl;

import com.univer.servlets.dao.AbstractJdbcDao;
import com.univer.servlets.dao.BookDao;
import com.univer.servlets.dao.UserDao;
import com.univer.servlets.entity.Book;
import com.univer.servlets.entity.Role;
import com.univer.servlets.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao {
    private static final String INSERT_QUERY = "INSERT INTO `USER`(LOGIN, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME, " +
            "BIRTHDAY, ROLE_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE `USER` SET LOGIN = ?, PASSWORD = ?," +
            "EMAIL = ?, FIRST_NAME = ?, LAST_NAME = ?, BIRTHDAY = ?, ROLE_ID = ? WHERE ID = ?";
    private static final String REMOVE_QUERY = "DELETE FROM USER_BOOK WHERE USER_ID = ?;" +
            "DELETE FROM `USER` WHERE ID = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT U.ID, U.LOGIN, U.PASSWORD, U.EMAIL, U.FIRST_NAME, " +
            "U.LAST_NAME, U.BIRTHDAY, U.ROLE_ID, R.NAME AS ROLE_NAME FROM `USER` U JOIN ROLE R " +
            "ON R.ID = U.ROLE_ID WHERE U.ID = ?";
    private static final String FIND_BY_LOGIN_QUERY = "SELECT U.ID, U.LOGIN, U.PASSWORD, U.EMAIL, U.FIRST_NAME, " +
            "U.LAST_NAME, U.BIRTHDAY, U.ROLE_ID, R.NAME AS ROLE_NAME FROM `USER` U JOIN ROLE R " +
            "ON R.ID = U.ROLE_ID WHERE U.LOGIN = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT U.ID, U.LOGIN, U.PASSWORD, U.EMAIL, U.FIRST_NAME, " +
            "U.LAST_NAME, U.BIRTHDAY, U.ROLE_ID, R.NAME AS ROLE_NAME FROM `USER` U JOIN ROLE R " +
            "ON R.ID = U.ROLE_ID WHERE U.EMAIL = ?";
    private static final String FIND_ALL_QUERY = "SELECT U.ID, U.LOGIN, U.PASSWORD, U.EMAIL, U.FIRST_NAME, " +
            "U.LAST_NAME, U.BIRTHDAY, U.ROLE_ID, R.NAME AS ROLE_NAME FROM `USER` U JOIN ROLE R " +
            "ON R.ID = U.ROLE_ID";
    private static final String INSERT_INTO_USER_BOOK_QUERY = "INSERT INTO USER_BOOK(USER_ID, BOOK_ID)" +
            " VALUES(?, ?)";

    @Override
    public void create(User user) {
        executeUpdateStatement(INSERT_QUERY, Arrays.asList(user.getLogin(), user.getPassword(),
                user.getEmail(), user.getFirstName(), user.getLastName(), user.getBirthday(), user.getRole().getId()));
    }

    @Override
    public void update(User user) {
        executeUpdateStatement(UPDATE_QUERY, Arrays.asList(user.getLogin(), user.getPassword(),
                user.getEmail(), user.getFirstName(), user.getLastName(),
                user.getBirthday(), user.getRole().getId(), user.getId()));
    }

    @Override
    public void remove(User user) {
        executeUpdateStatement(REMOVE_QUERY, Arrays.asList(user.getId(), user.getId()));
    }

    @Override
    public void addBookToUser(Long userId, Long bookId) {
        executeUpdateStatement(INSERT_INTO_USER_BOOK_QUERY, Arrays.asList(userId, bookId));
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ID_COLUMN_NAME));
                user.setLogin(resultSet.getString(LOGIN_COLUMN_NAME));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));
                user.setEmail(resultSet.getString(EMAIL_COLUMN_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN_NAME));
                user.setLastName(resultSet.getString(LAST_NAME_COLUMN_NAME));
                user.setBirthday(resultSet.getDate(BIRTHDAY_COLUMN_NAME));
                user.setRole(new Role(resultSet.getLong(ROLE_ID_COLUMN_NAME), resultSet.getString(ROLE_NAME_COLUMN_NAME)));
                BookDao bookDao = new JdbcBookDao();
                user.setBooks(bookDao.findByUser(user));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_QUERY)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ID_COLUMN_NAME));
                user.setLogin(resultSet.getString(LOGIN_COLUMN_NAME));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));
                user.setEmail(resultSet.getString(EMAIL_COLUMN_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN_NAME));
                user.setLastName(resultSet.getString(LAST_NAME_COLUMN_NAME));
                user.setBirthday(resultSet.getDate(BIRTHDAY_COLUMN_NAME));
                user.setRole(new Role(resultSet.getLong(ROLE_ID_COLUMN_NAME), resultSet.getString(ROLE_NAME_COLUMN_NAME)));
                BookDao bookDao = new JdbcBookDao();
                user.setBooks(bookDao.findByUser(user));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ID_COLUMN_NAME));
                user.setLogin(resultSet.getString(LOGIN_COLUMN_NAME));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));
                user.setEmail(resultSet.getString(EMAIL_COLUMN_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN_NAME));
                user.setLastName(resultSet.getString(LAST_NAME_COLUMN_NAME));
                user.setBirthday(resultSet.getDate(BIRTHDAY_COLUMN_NAME));
                user.setRole(new Role(resultSet.getLong(ROLE_ID_COLUMN_NAME), resultSet.getString(ROLE_NAME_COLUMN_NAME)));
                BookDao bookDao = new JdbcBookDao();
                user.setBooks(bookDao.findByUser(user));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(ID_COLUMN_NAME));
                user.setLogin(resultSet.getString(LOGIN_COLUMN_NAME));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));
                user.setEmail(resultSet.getString(EMAIL_COLUMN_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN_NAME));
                user.setLastName(resultSet.getString(LAST_NAME_COLUMN_NAME));
                user.setBirthday(resultSet.getDate(BIRTHDAY_COLUMN_NAME));
                user.setRole(new Role(resultSet.getLong(ROLE_ID_COLUMN_NAME), resultSet.getString(ROLE_NAME_COLUMN_NAME)));
                BookDao bookDao = new JdbcBookDao();
                user.setBooks(bookDao.findByUser(user));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
