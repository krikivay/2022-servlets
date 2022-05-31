package com.univer.servlets.dao;

import com.univer.servlets.entity.Book;
import com.univer.servlets.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    void update(User user);
    void remove(User user);
    User findById(Long id);
    User findByLogin(String login);
    User findByEmail(String email);
    List<User> findAll();
    void addBookToUser(Long userId, Long bookId);
}
