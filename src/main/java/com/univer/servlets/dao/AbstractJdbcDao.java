package com.univer.servlets.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public abstract class AbstractJdbcDao {
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static final String DB_DRIVER = "DB_DRIVER";
    private static final String DB_URL = "DB_URL";
    private static final String DB_USERNAME = "DB_USERNAME";
    private static final String DB_PASSWORD = "DB_PASSWORD";
    private static final BasicDataSource CONNECTION_POOL = new BasicDataSource();
    private static final Properties PROPERTIES = new Properties();


    protected static final String ID_COLUMN_NAME = "ID";
    protected static final String LOGIN_COLUMN_NAME = "LOGIN";
    protected static final String PASSWORD_COLUMN_NAME = "PASSWORD";
    protected static final String EMAIL_COLUMN_NAME = "EMAIL";
    protected static final String FIRST_NAME_COLUMN_NAME = "FIRST_NAME";
    protected static final String LAST_NAME_COLUMN_NAME = "LAST_NAME";
    protected static final String BIRTHDAY_COLUMN_NAME = "BIRTHDAY";
    protected static final String ROLE_ID_COLUMN_NAME = "ROLE_ID";
    protected static final String ROLE_NAME_COLUMN_NAME = "ROLE_NAME";

    static {
        try (InputStream inputStream = AbstractJdbcDao.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
            PROPERTIES.load(inputStream);
            CONNECTION_POOL.setDriverClassName(PROPERTIES.getProperty(DB_DRIVER));
            CONNECTION_POOL.setUrl(PROPERTIES.getProperty(DB_URL));
            CONNECTION_POOL.setUsername(PROPERTIES.getProperty(DB_USERNAME));
            CONNECTION_POOL.setPassword(PROPERTIES.getProperty(DB_PASSWORD));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection createConnection() throws SQLException {
        Connection connection = CONNECTION_POOL.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    protected void executeUpdateStatement(String query, List<Object> bindVariables) {
        try (Connection connection = createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (int i = 0; i < bindVariables.size(); i++) {
                    preparedStatement.setObject(i + 1, bindVariables.get(i));
                }
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
