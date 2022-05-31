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
import static com.univer.servlets.constants.Constants.MY_PROFILE_PAGE_PATH;
import static com.univer.servlets.constants.Constants.MY_PROFILE_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.MY_PROFILE_SERVLET_URL;
import static com.univer.servlets.constants.Constants.USER_SESSION_ATTRIBUTE;

@WebServlet(name = MY_PROFILE_SERVLET_NAME, value = MY_PROFILE_SERVLET_URL)
public class MyProfileServlet extends HttpServlet {

    private UserDao userDao = new JdbcUserDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserSimpleModel userSimpleModel = (UserSimpleModel) session.getAttribute(USER_SESSION_ATTRIBUTE);
        User user = userDao.findById(userSimpleModel.getId());
        session.setAttribute(FULL_USER_ATTRIBUTE, user);
        session.removeAttribute(ALL_USERS_ATTRIBUTE);
        response.sendRedirect(MY_PROFILE_PAGE_PATH);
    }
}
