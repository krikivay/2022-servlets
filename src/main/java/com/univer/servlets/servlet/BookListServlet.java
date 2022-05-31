package com.univer.servlets.servlet;

import com.univer.servlets.dao.BookDao;
import com.univer.servlets.dao.impl.JdbcBookDao;
import com.univer.servlets.entity.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import static com.univer.servlets.constants.Constants.ALL_BOOKS_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.ALL_USERS_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.BOOK_LIST_PAGE_PATH;
import static com.univer.servlets.constants.Constants.BOOK_LIST_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.BOOK_LIST_SERVLET_URL;
import static com.univer.servlets.constants.Constants.FULL_USER_ATTRIBUTE;

@WebServlet(name = BOOK_LIST_SERVLET_NAME, value = BOOK_LIST_SERVLET_URL)
public class BookListServlet extends HttpServlet {
    private BookDao bookDao = new JdbcBookDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(FULL_USER_ATTRIBUTE);
        session.removeAttribute(ALL_USERS_ATTRIBUTE);
        List<Book> books = bookDao.findAll();
        session.setAttribute(ALL_BOOKS_ATTRIBUTE, books);
        response.sendRedirect(BOOK_LIST_PAGE_PATH);
    }
}
