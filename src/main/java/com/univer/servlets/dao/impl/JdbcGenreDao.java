package com.univer.servlets.dao.impl;

import com.univer.servlets.dao.AbstractJdbcDao;
import com.univer.servlets.dao.GenreDao;
import com.univer.servlets.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JdbcGenreDao extends AbstractJdbcDao implements GenreDao {
    private static final String INSERT_QUERY = "INSERT INTO GENRE(NAME) VALUES(?)";
    private static final String UPDATE_QUERY = "UPDATE GENRE SET NAME = ? WHERE ID = ?";
    private static final String REMOVE_QUERY = "DELETE FROM GENRE WHERE ID = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM GENRE";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM GENRE WHERE NAME = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM GENRE WHERE ID = ?";
    private static final String ID_COLUMN_NAME = "ID";
    private static final String NAME_COLUMN_NAME = "NAME";

    @Override
    public void create(Genre genre) {
        executeUpdateStatement(INSERT_QUERY, Collections.singletonList(genre.getName()));
    }

    @Override
    public void update(Genre genre) {
        executeUpdateStatement(UPDATE_QUERY, Arrays.asList(genre.getName(), genre.getId()));
    }

    @Override
    public void remove(Genre genre) {
        executeUpdateStatement(REMOVE_QUERY, Collections.singletonList(genre.getId()));
    }

    @Override
    public List<Genre> findAll() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            List<Genre> genres = new ArrayList<>();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getLong(ID_COLUMN_NAME));
                genre.setName(resultSet.getString(NAME_COLUMN_NAME));
                genres.add(genre);
            }
            return genres;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Genre findByName(String name) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_QUERY)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getLong(ID_COLUMN_NAME));
                genre.setName(resultSet.getString(NAME_COLUMN_NAME));
                return genre;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Genre findById(Long id) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getLong(ID_COLUMN_NAME));
                genre.setName(resultSet.getString(NAME_COLUMN_NAME));
                return genre;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
