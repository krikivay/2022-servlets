package com.univer.servlets.servlet;

import com.univer.servlets.dao.UserDao;
import com.univer.servlets.dao.impl.JdbcUserDao;
import com.univer.servlets.entity.UserModelForUserList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.univer.servlets.constants.Constants.ALL_USERS_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.FULL_USER_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.USER_LIST_PAGE_PATH;
import static com.univer.servlets.constants.Constants.USER_LIST_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.USER_LIST_SERVLET_URL;

@WebServlet(name = USER_LIST_SERVLET_NAME, value = USER_LIST_SERVLET_URL)
public class UserListServlet extends HttpServlet {
    private UserDao userDao = new JdbcUserDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<UserModelForUserList> users = userDao.findAll()
                .stream()
                .map(UserModelForUserList::new)
                .collect(Collectors.toList());
        HttpSession session = request.getSession();
        session.setAttribute(ALL_USERS_ATTRIBUTE, users);
        session.removeAttribute(FULL_USER_ATTRIBUTE);
        response.sendRedirect(USER_LIST_PAGE_PATH);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
