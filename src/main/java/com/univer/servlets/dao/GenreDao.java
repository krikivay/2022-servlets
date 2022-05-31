package com.univer.servlets.dao;

import com.univer.servlets.entity.Genre;

import java.util.List;

public interface GenreDao {
    void create(Genre genre);
    void update(Genre genre);
    void remove(Genre genre);
    List<Genre> findAll();
    Genre findByName(String name);
    Genre findById(Long id);
}
