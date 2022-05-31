package com.univer.servlets.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.univer.servlets.constants.Constants.ALL_USERS_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.FULL_USER_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.LOGIN_PAGE_PATH;
import static com.univer.servlets.constants.Constants.LOGOUT_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.LOGOUT_SERVLET_URL;
import static com.univer.servlets.constants.Constants.USER_SESSION_ATTRIBUTE;

@WebServlet(name = LOGOUT_SERVLET_NAME, value = LOGOUT_SERVLET_URL)
public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(USER_SESSION_ATTRIBUTE);
        session.removeAttribute(FULL_USER_ATTRIBUTE);
        session.removeAttribute(ALL_USERS_ATTRIBUTE);
        response.sendRedirect(LOGIN_PAGE_PATH);
    }
}
