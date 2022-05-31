package com.univer.servlets.servlet;

import com.univer.servlets.dao.UserDao;
import com.univer.servlets.dao.impl.JdbcUserDao;
import com.univer.servlets.entity.User;
import com.univer.servlets.entity.UserSimpleModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.univer.servlets.constants.Constants.DELETE_USER_ID_REQUEST_PARAMETER;
import static com.univer.servlets.constants.Constants.DELETE_USER_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.DELETE_USER_SERVLET_URL;
import static com.univer.servlets.constants.Constants.LOGIN_PAGE_PATH;
import static com.univer.servlets.constants.Constants.LOGIN_SERVLET_URL;
import static com.univer.servlets.constants.Constants.USER_LIST_PAGE_PATH;
import static com.univer.servlets.constants.Constants.USER_LIST_SERVLET_URL;
import static com.univer.servlets.constants.Constants.USER_SESSION_ATTRIBUTE;

@WebServlet(name = DELETE_USER_SERVLET_NAME, value = DELETE_USER_SERVLET_URL)
public class DeleteUserServlet extends HttpServlet {

    private UserDao userDao = new JdbcUserDao();

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDao.findById(Long.valueOf(request.getParameter(DELETE_USER_ID_REQUEST_PARAMETER)));
        userDao.remove(user);
        String pageToRedirect;
        if (user.getId().equals(((UserSimpleModel)request.getSession().getAttribute(USER_SESSION_ATTRIBUTE)).getId())) {
            pageToRedirect = LOGIN_SERVLET_URL;
        } else {
            pageToRedirect = USER_LIST_SERVLET_URL;
        }
        request.getRequestDispatcher(pageToRedirect).forward(request, response);
    }
}
