package com.univer.servlets.entity;

import java.util.List;
import java.util.Objects;

public class Book {
    private Long id;
    private String author;
    private String title;
    private Double price;
    private Genre genre;
    private List<User> users;

    public Book() {
    }

    public Book(Long id, String author, String title, Double price, Genre genre) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.genre = genre;
    }

    public Book(String author, String title, Double price, Genre genre) {
        this(null, author, title, price, genre);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(price, book.price) && Objects.equals(genre, book.genre) && Objects.equals(users, book.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, price, genre, users);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", genre=" + genre +
                '}';
    }
}
