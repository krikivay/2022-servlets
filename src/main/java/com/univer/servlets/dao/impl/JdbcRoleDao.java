package com.univer.servlets.dao.impl;

import com.univer.servlets.dao.AbstractJdbcDao;
import com.univer.servlets.dao.RoleDao;
import com.univer.servlets.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {
    private static final String INSERT_QUERY = "INSERT INTO ROLE(NAME) VALUES(?)";
    private static final String UPDATE_QUERY = "UPDATE ROLE SET NAME = ? WHERE ID = ?";
    private static final String REMOVE_QUERY = "DELETE FROM ROLE WHERE ID = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM ROLE";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM ROLE WHERE NAME = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM ROLE WHERE ID = ?";
    private static final String ID_COLUMN_NAME = "ID";
    private static final String NAME_COLUMN_NAME = "NAME";

    @Override
    public void create(Role role) {
        executeUpdateStatement(INSERT_QUERY, Collections.singletonList(role.getName()));
    }

    @Override
    public void update(Role role) {
        executeUpdateStatement(UPDATE_QUERY, Arrays.asList(role.getName(), role.getId()));
    }

    @Override
    public void remove(Role role) {
        executeUpdateStatement(REMOVE_QUERY, Collections.singletonList(role.getId()));
    }

    @Override
    public List<Role> findAll() {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(ID_COLUMN_NAME));
                role.setName(resultSet.getString(NAME_COLUMN_NAME));
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role findByName(String name) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_QUERY)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(ID_COLUMN_NAME));
                role.setName(resultSet.getString(NAME_COLUMN_NAME));
                return role;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role findById(Long id) {
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(ID_COLUMN_NAME));
                role.setName(resultSet.getString(NAME_COLUMN_NAME));
                return role;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
