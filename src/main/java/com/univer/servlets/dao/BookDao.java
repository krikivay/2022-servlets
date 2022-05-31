package com.univer.servlets.dao;

import com.univer.servlets.entity.Book;
import com.univer.servlets.entity.Genre;
import com.univer.servlets.entity.User;

import java.util.List;

public interface BookDao {
    void create(Book book);
    void update(Book book);
    void remove(Book book);
    List<Book> findAll();
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    Book findById(Long id);
    List<Book> findByGenre(Genre genre);
    List<Book> findByUser(User user);
}
