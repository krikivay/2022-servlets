package com.univer.servlets.dao.impl;

import com.univer.servlets.dao.AbstractJdbcDao;
import com.univer.servlets.dao.BookDao;
import com.univer.servlets.entity.Book;
import com.univer.servlets.entity.Genre;
import com.univer.servlets.entity.Role;
import com.univer.servlets.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JdbcBookDao extends AbstractJdbcDao implements BookDao {
    private static final String INSERT_QUERY = "INSERT INTO BOOK(AUTHOR, TITLE, PRICE, GENRE_ID) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE BOOK SET AUTHOR = ?, TITLE = ?, PRICE = ?, GENRE_ID = ? WHERE ID = ?";
    private static final String REMOVE_QUERY = "DELETE FROM BOOK WHERE ID = ?";
    private static final String FIND_ALL_QUERY = "SELECT B.ID, B.AUTHOR, B.TITLE, B.PRICE, B.GENRE_ID, G.NAME AS GENRE_NAME " +
            "FROM BOOK B JOIN GENRE G ON B.GENRE_ID = G.ID";
    private static final String FIND_BY_AUTHOR_QUERY = "SELECT B.ID, B.AUTHOR, B.TITLE, B.PRICE, B.GENRE_ID, " +
            "G.NAME AS GENRE_NAME FROM BOOK B JOIN GENRE G ON B.GENRE_ID = G.ID WHERE B.AUTHOR = ?";
    private static final String FIND_BY_TITLE_QUERY = "SELECT B.ID, B.AUTHOR, B.TITLE, B.PRICE, B.GENRE_ID, " +
            "G.NAME AS GENRE_NAME FROM BOOK B JOIN GENRE G ON B.GENRE_ID = G.ID WHERE B.TITLE = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT B.ID, B.AUTHOR, B.TITLE, B.PRICE, B.GENRE_ID, " +
            "G.NAME AS GENRE_NAME FROM BOOK B JOIN GENRE G ON B.GENRE_ID = G.ID WHERE B.ID = ?";
    private static final String FIND_BY_GENRE_QUERY = "SELECT B.ID, B.AUTHOR, B.TITLE, B.PRICE, B.GENRE_ID, " +
            "G.NAME AS GENRE_NAME FROM BOOK B JOIN GENRE G ON B.GENRE_ID = G.ID WHERE B.GENRE_ID = ?";
    private static final String FIND_BY_USER_QUERY = "SELECT B.ID, B.AUTHOR, B.TITLE, B.PRICE, B.GENRE_ID, " +
            "G.NAME AS GENRE_NAME FROM BOOK B JOIN GENRE G ON G.ID = B.GENRE_ID " +
            "JOIN USER_BOOK UB ON UB.BOOK_ID = B.ID JOIN `USER` U ON UB.USER_ID = U.ID WHERE U.ID = ?";
    private static final String FIND_BOOK_OWNERS_QUERY = "SELECT U.ID, U.LOGIN, U.PASSWORD, U.EMAIL, U.FIRST_NAME, " +
            "U.LAST_NAME, U.BIRTHDAY, U.ROLE_ID, R.NAME AS ROLE_NAME FROM `USER` U JOIN ROLE R ON R.ID = U.ROLE_ID " +
            "JOIN USER_BOOK UB ON U.ID = UB.USER_ID JOIN BOOK B ON UB.BOOK_ID = B.ID WHERE B.ID = ?";
    private static final String ID_COLUMN_NAME = "ID";
    private static final String AUTHOR_COLUMN_NAME = "AUTHOR";
    private static final String TITLE_COLUMN_NAME = "TITLE";
    private static final String PRICE_COLUMN_NAME = "PRICE";
    private static final String GENRE_ID_COLUMN_NAME = "GENRE_ID";
    private static final String GENRE_NAME_COLUMN_NAME = "GENRE_NAME";

    @Override
    public void create(Book book) {
        executeUpdateStatement(INSERT_QUERY,
                Arrays.asList(book.getAuthor(), book.getTitle(), book.getPrice(), book.getGenre().getId()));
    }

    @Override
    public void update(Book book) {
        executeUpdateStatement(UPDATE_QUERY,
                Arrays.asList(book.getAuthor(), book.getTitle(), book.getPrice(), book.getGenre().getId(), book.getId()));
    }

    @Override
    public void remove(Book book) {
        executeUpdateStatement(REMOVE_QUERY, Collections.singletonList(book.getId()));
    }

    @Override
    public List<Book> findAll() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(ID_COLUMN_NAME));
                book.setAuthor(resultSet.getString(AUTHOR_COLUMN_NAME));
                book.setTitle(resultSet.getString(TITLE_COLUMN_NAME));
                book.setPrice(resultSet.getDouble(PRICE_COLUMN_NAME));
                book.setGenre(new Genre(resultSet.getLong(GENRE_ID_COLUMN_NAME), resultSet.getString(GENRE_NAME_COLUMN_NAME)));
                book.setUsers(findBookOwners(book));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findByAuthor(String author) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_AUTHOR_QUERY)) {
            preparedStatement.setString(1, author);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(ID_COLUMN_NAME));
                book.setAuthor(resultSet.getString(AUTHOR_COLUMN_NAME));
                book.setTitle(resultSet.getString(TITLE_COLUMN_NAME));
                book.setPrice(resultSet.getDouble(PRICE_COLUMN_NAME));
                book.setGenre(new Genre(resultSet.getLong(GENRE_ID_COLUMN_NAME), resultSet.getString(GENRE_NAME_COLUMN_NAME)));
                book.setUsers(findBookOwners(book));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findByTitle(String title) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TITLE_QUERY)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(ID_COLUMN_NAME));
                book.setAuthor(resultSet.getString(AUTHOR_COLUMN_NAME));
                book.setTitle(resultSet.getString(TITLE_COLUMN_NAME));
                book.setPrice(resultSet.getDouble(PRICE_COLUMN_NAME));
                book.setGenre(new Genre(resultSet.getLong(GENRE_ID_COLUMN_NAME), resultSet.getString(GENRE_NAME_COLUMN_NAME)));
                book.setUsers(findBookOwners(book));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findById(Long id) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(ID_COLUMN_NAME));
                book.setAuthor(resultSet.getString(AUTHOR_COLUMN_NAME));
                book.setTitle(resultSet.getString(TITLE_COLUMN_NAME));
                book.setPrice(resultSet.getDouble(PRICE_COLUMN_NAME));
                book.setGenre(new Genre(resultSet.getLong(GENRE_ID_COLUMN_NAME), resultSet.getString(GENRE_NAME_COLUMN_NAME)));
                book.setUsers(findBookOwners(book));
                return book;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_GENRE_QUERY)) {
            preparedStatement.setLong(1, genre.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(ID_COLUMN_NAME));
                book.setAuthor(resultSet.getString(AUTHOR_COLUMN_NAME));
                book.setTitle(resultSet.getString(TITLE_COLUMN_NAME));
                book.setPrice(resultSet.getDouble(PRICE_COLUMN_NAME));
                book.setGenre(new Genre(resultSet.getLong(GENRE_ID_COLUMN_NAME), resultSet.getString(GENRE_NAME_COLUMN_NAME)));
                book.setUsers(findBookOwners(book));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findByUser(User user) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_QUERY)) {
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(ID_COLUMN_NAME));
                book.setAuthor(resultSet.getString(AUTHOR_COLUMN_NAME));
                book.setTitle(resultSet.getString(TITLE_COLUMN_NAME));
                book.setPrice(resultSet.getDouble(PRICE_COLUMN_NAME));
                book.setGenre(new Genre(resultSet.getLong(GENRE_ID_COLUMN_NAME), resultSet.getString(GENRE_NAME_COLUMN_NAME)));
                book.setUsers(findBookOwners(book));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> findBookOwners(Book book) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOK_OWNERS_QUERY)) {
            preparedStatement.setLong(1, book.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
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
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
