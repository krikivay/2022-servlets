package com.univer.servlets.validation;

import com.univer.servlets.dao.UserDao;
import com.univer.servlets.dao.impl.JdbcUserDao;
import com.univer.servlets.servlet.AddUserServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.univer.servlets.constants.Constants.ADD_EDIT_USER_PAGE_PATH;
import static com.univer.servlets.constants.Constants.ADD_PAGE_FLAG;
import static com.univer.servlets.constants.Constants.CONFIRM_PASSWORD;
import static com.univer.servlets.constants.Constants.EMAIL;
import static com.univer.servlets.constants.Constants.EMAIL_PATTERN_ERROR_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.EMAIL_PATTERN_ERROR_MESSAGE;
import static com.univer.servlets.constants.Constants.EMAIL_UNIQUE_ERROR_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.EMAIL_UNIQUE_ERROR_MESSAGE;
import static com.univer.servlets.constants.Constants.LOGIN;
import static com.univer.servlets.constants.Constants.LOGIN_ERROR_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.LOGIN_ERROR_MESSAGE;
import static com.univer.servlets.constants.Constants.LOGIN_UNIQUE_ERROR_MESSAGE;
import static com.univer.servlets.constants.Constants.PASSWORD;
import static com.univer.servlets.constants.Constants.PASSWORDS_MATCH_ERROR_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.PASSWORDS_MATCH_ERROR_MESSAGE;
import static com.univer.servlets.constants.Constants.PASSWORD_LENGTH_ATTRIBUTE;
import static com.univer.servlets.constants.Constants.PASSWORD_LENGTH_ERROR_MESSAGE;

public class Validation {

    private static final UserDao USER_DAO = new JdbcUserDao();

    private enum ERROR_TYPE {
        LOGIN,
        PASSWORD_LENGTH,
        PASSWORD_MATCH,
        EMAIL_PATTERN,
        EMAIL_UNIQUE
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^([a-zA-Z0-9._]+)@([a-zA-Z0-9]+)\\.([a-zA-Z])+\\.([a-zA-Z])+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean arePasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean isPasswordLengthValid(String password) {
        return password.length() >= 8;
    }

    public static boolean areFieldsValid(HttpServletRequest request, String method) {

        boolean hasError = false;

        String login = request.getParameter(LOGIN);
        if (method.equals(ADD_PAGE_FLAG) && USER_DAO.findByLogin(login) != null) {
            setErrorMessages(request, ERROR_TYPE.LOGIN);
            hasError = true;
        }

        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
        if (!Validation.arePasswordsMatch(password, confirmPassword)) {
            setErrorMessages(request, ERROR_TYPE.PASSWORD_MATCH);
            hasError = true;
        }
        if (!Validation.isPasswordLengthValid(password)) {
            setErrorMessages(request, ERROR_TYPE.PASSWORD_LENGTH);
            hasError = true;
        }

        String email = request.getParameter(EMAIL);
        if (method.equals(ADD_PAGE_FLAG) && USER_DAO.findByEmail(email) != null) {
            setErrorMessages(request, ERROR_TYPE.EMAIL_UNIQUE);
            hasError = true;
        }
        if (!Validation.isEmailValid(email)) {
            setErrorMessages(request, ERROR_TYPE.EMAIL_PATTERN);
            hasError = true;
        }

        return !hasError;
    }

    private static void setErrorMessages(HttpServletRequest request, ERROR_TYPE error_type) {
        switch (error_type) {
            case LOGIN:
                request.getSession().setAttribute(LOGIN_ERROR_ATTRIBUTE, LOGIN_UNIQUE_ERROR_MESSAGE);
            case PASSWORD_MATCH:
                request.getSession().setAttribute(PASSWORDS_MATCH_ERROR_ATTRIBUTE, PASSWORDS_MATCH_ERROR_MESSAGE);
            case PASSWORD_LENGTH:
                request.getSession().setAttribute(PASSWORD_LENGTH_ATTRIBUTE, PASSWORD_LENGTH_ERROR_MESSAGE);
            case EMAIL_UNIQUE:
                request.getSession().setAttribute(EMAIL_UNIQUE_ERROR_ATTRIBUTE, EMAIL_UNIQUE_ERROR_MESSAGE);
            case EMAIL_PATTERN:
                request.getSession().setAttribute(EMAIL_PATTERN_ERROR_ATTRIBUTE, EMAIL_PATTERN_ERROR_MESSAGE);
        }
    }

    public static void removeErrorMessages(HttpSession session) {
        session.removeAttribute(LOGIN_ERROR_ATTRIBUTE);
        session.removeAttribute(PASSWORDS_MATCH_ERROR_ATTRIBUTE);
        session.removeAttribute(PASSWORD_LENGTH_ATTRIBUTE);
        session.removeAttribute(EMAIL_UNIQUE_ERROR_ATTRIBUTE);
        session.removeAttribute(EMAIL_PATTERN_ERROR_ATTRIBUTE);
    }
}
