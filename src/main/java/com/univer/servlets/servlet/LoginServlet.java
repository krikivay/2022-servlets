package com.univer.servlets.servlet;

import com.univer.servlets.dao.UserDao;
import com.univer.servlets.dao.impl.JdbcUserDao;
import com.univer.servlets.entity.User;
import com.univer.servlets.entity.UserSimpleModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.univer.servlets.constants.Constants.ALL_USERS_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.FULL_USER_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.HOME_PAGE_PATH;
import static com.univer.servlets.constants.Constants.LOGIN_ERROR_MESSAGE;
import static com.univer.servlets.constants.Constants.LOGIN_ERROR_SESSION_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.LOGIN_FORM_PARAMETER;
import static com.univer.servlets.constants.Constants.LOGIN_PAGE_PATH;
import static com.univer.servlets.constants.Constants.LOGIN_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.LOGIN_SERVLET_URL;
import static com.univer.servlets.constants.Constants.PASSWORD_FORM_PARAMETER;
import static com.univer.servlets.constants.Constants.USER_SESSION_ATTRIBUTE;


@WebServlet(name = LOGIN_SERVLET_NAME, value = LOGIN_SERVLET_URL)
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new JdbcUserDao();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN_FORM_PARAMETER);
        User user = userDao.findByLogin(login);
        String password = request.getParameter(PASSWORD_FORM_PARAMETER);
        HttpSession session = request.getSession();
        session.removeAttribute(FULL_USER_ATTRIBUTE);
        session.removeAttribute(ALL_USERS_ATTRIBUTE);
        if (user != null && user.getPassword().equals(password)) {
            session.removeAttribute(LOGIN_ERROR_SESSION_ATTRIBUTE);
            session.setAttribute(USER_SESSION_ATTRIBUTE, new UserSimpleModel(user));
            response.sendRedirect(HOME_PAGE_PATH);
        } else {
            session.setAttribute(LOGIN_ERROR_SESSION_ATTRIBUTE, LOGIN_ERROR_MESSAGE);
            response.sendRedirect(LOGIN_PAGE_PATH);
        }
    }
}
