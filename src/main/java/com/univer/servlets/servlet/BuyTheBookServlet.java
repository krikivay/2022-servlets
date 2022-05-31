package com.univer.servlets.servlet;

import com.univer.servlets.dao.BookDao;
import com.univer.servlets.dao.UserDao;
import com.univer.servlets.dao.impl.JdbcBookDao;
import com.univer.servlets.dao.impl.JdbcUserDao;
import com.univer.servlets.entity.Book;
import com.univer.servlets.entity.UserSimpleModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.univer.servlets.constants.Constants.BUY_THE_BOOK_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.BUY_THE_BOOK_SERVLET_URL;
import static com.univer.servlets.constants.Constants.ID;
import static com.univer.servlets.constants.Constants.MY_PROFILE_PAGE_PATH;
import static com.univer.servlets.constants.Constants.MY_PROFILE_SERVLET_URL;
import static com.univer.servlets.constants.Constants.USER_SESSION_ATTRIBUTE;

@WebServlet(name = BUY_THE_BOOK_SERVLET_NAME, value = BUY_THE_BOOK_SERVLET_URL)
public class BuyTheBookServlet extends HttpServlet {

    private BookDao bookDao = new JdbcBookDao();
    private UserDao userDao = new JdbcUserDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.valueOf(request.getParameter(ID));
        Book book = bookDao.findById(id);
        UserSimpleModel user = (UserSimpleModel) request.getSession().getAttribute(USER_SESSION_ATTRIBUTE);

        userDao.addBookToUser(user.getId(), book.getId());

        request.getRequestDispatcher(MY_PROFILE_SERVLET_URL).forward(request, response);
    }
}
