package com.univer.servlets.servlet;

import com.univer.servlets.dao.RoleDao;
import com.univer.servlets.dao.UserDao;
import com.univer.servlets.dao.impl.JdbcRoleDao;
import com.univer.servlets.dao.impl.JdbcUserDao;
import com.univer.servlets.entity.Role;
import com.univer.servlets.entity.User;
import com.univer.servlets.validation.Validation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

import static com.univer.servlets.constants.Constants.ADD_EDIT_MODE;
import static com.univer.servlets.constants.Constants.ADD_EDIT_USER_PAGE_PATH;
import static com.univer.servlets.constants.Constants.BIRTHDAY;
import static com.univer.servlets.constants.Constants.EDIT_PAGE_FLAG;
import static com.univer.servlets.constants.Constants.EDIT_USER_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.EDIT_USER_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.EDIT_USER_SERVLET_URL;
import static com.univer.servlets.constants.Constants.EMAIL;
import static com.univer.servlets.constants.Constants.FIRST_NAME;
import static com.univer.servlets.constants.Constants.ID;
import static com.univer.servlets.constants.Constants.LAST_NAME;
import static com.univer.servlets.constants.Constants.LOGIN;
import static com.univer.servlets.constants.Constants.PASSWORD;
import static com.univer.servlets.constants.Constants.ROLE;
import static com.univer.servlets.constants.Constants.ROLES_ATTRIBUTE_NAME;
import static com.univer.servlets.constants.Constants.USER_LIST_PAGE_PATH;

@WebServlet(name = EDIT_USER_SERVLET_NAME, value = EDIT_USER_SERVLET_URL)
public class EditUserServlet extends HttpServlet {

    private RoleDao roleDao = new JdbcRoleDao();
    private UserDao userDao = new JdbcUserDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Validation.removeErrorMessages(session);
        session.setAttribute(ROLES_ATTRIBUTE_NAME, roleDao.findAll());
        User user = userDao.findById(Long.valueOf(request.getParameter(ID)));
        session.setAttribute(EDIT_USER_ATTRIBUTE, user);
        session.setAttribute(ADD_EDIT_MODE, EDIT_PAGE_FLAG);
        response.sendRedirect(ADD_EDIT_USER_PAGE_PATH);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        if (!Validation.areFieldsValid(request, EDIT_PAGE_FLAG)) {
            response.sendRedirect(ADD_EDIT_USER_PAGE_PATH);
            return;
        }
        Role role = roleDao.findByName(request.getParameter(ROLE));
        Long id = Long.valueOf(request.getParameter(ID));
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        Date birthday = Date.valueOf(request.getParameter(BIRTHDAY));
        User user = userDao.findById(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        user.setRole(role);

        userDao.update(user);

        session.removeAttribute(ROLES_ATTRIBUTE_NAME);
        session.removeAttribute(EDIT_USER_ATTRIBUTE);
        session.removeAttribute(ADD_EDIT_MODE);

        response.sendRedirect(USER_LIST_PAGE_PATH);
    }
}
