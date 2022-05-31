package com.univer.servlets.filter;

import com.univer.servlets.entity.UserSimpleModel;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.univer.servlets.constants.Constants.ADD_EDIT_USER_PAGE_PATH;
import static com.univer.servlets.constants.Constants.ADD_NEW_USER_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.ADD_NEW_USER_SERVLET_URL;
import static com.univer.servlets.constants.Constants.BOOK_LIST_PAGE_PATH;
import static com.univer.servlets.constants.Constants.BOOK_LIST_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.DELETE_USER_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.DELETE_USER_SERVLET_URL;
import static com.univer.servlets.constants.Constants.HOME_PAGE_PATH;
import static com.univer.servlets.constants.Constants.LOGIN_PAGE_PATH;
import static com.univer.servlets.constants.Constants.LOGOUT_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.MY_PROFILE_PAGE_PATH;
import static com.univer.servlets.constants.Constants.MY_PROFILE_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.USER_LIST_PAGE_PATH;
import static com.univer.servlets.constants.Constants.USER_LIST_SERVLET_NAME;
import static com.univer.servlets.constants.Constants.USER_SESSION_ATTRIBUTE;

@WebFilter(
        filterName = "loginFilter",
        urlPatterns = {
                "/" + HOME_PAGE_PATH,
                "/" + BOOK_LIST_PAGE_PATH,
                "/" + USER_LIST_PAGE_PATH,
                "/" + MY_PROFILE_PAGE_PATH,
                DELETE_USER_SERVLET_URL,
                "/" + ADD_EDIT_USER_PAGE_PATH,
                ADD_NEW_USER_SERVLET_URL
        },
        servletNames = {
                LOGOUT_SERVLET_NAME,
                BOOK_LIST_SERVLET_NAME,
                USER_LIST_SERVLET_NAME,
                MY_PROFILE_SERVLET_NAME,
                DELETE_USER_SERVLET_NAME,
                ADD_NEW_USER_SERVLET_NAME
        }
)
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserSimpleModel userSimpleModel =
                (UserSimpleModel) request.getSession().getAttribute(USER_SESSION_ATTRIBUTE);
        if (userSimpleModel == null) {
            response.sendRedirect(LOGIN_PAGE_PATH);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
