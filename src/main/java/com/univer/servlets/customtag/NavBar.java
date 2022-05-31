package com.univer.servlets.customtag;

import com.univer.servlets.entity.UserSimpleModel;
import org.h2.util.StringUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static com.univer.servlets.constants.Constants.ADMIN_ROLE_NAME;
import static com.univer.servlets.constants.Constants.USER_SESSION_ATTRIBUTE;

public class NavBar extends SimpleTagSupport {

    public void doTag() throws IOException {
       UserSimpleModel currentUser = ((UserSimpleModel) getJspContext()
               .getAttribute(USER_SESSION_ATTRIBUTE, PageContext.SESSION_SCOPE));
       String userListNavItem = currentUser != null
               && currentUser.getRole().getName().equals(ADMIN_ROLE_NAME) ?
                "<li class=\"nav-item\">\n" +
                "<a class=\"nav-link\" href=\"userlist\">User List</a>\n" +
                "</li>\n" : "";
        String navBar = "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
                "    <div class=\"container-fluid\">\n" +
                "        <a class=\"navbar-brand\" href=\"home-page.jsp\">\n" +
                "            <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"32\" height=\"32\" fill=\"currentColor\" class=\"bi bi-book\"\n" +
                "                 viewBox=\"0 0 16 16\">\n" +
                "                <path d=\"M1 2.828c.885-.37 2.154-.769 3.388-.893 1.33-.134 2.458.063 3.112.752v9.746c-.935-.53-2.12-.603-3.213-.493-1.18.12-2.37.461-3.287.811V2.828zm7.5-.141c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492V2.687zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z\"/>\n" +
                "            </svg>\n" +
                "            Book Store\n" +
                "        </a>\n" +
                "        <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\"\n" +
                "                aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "            <span class=\"navbar-toggler-icon\"></span>\n" +
                "        </button>\n" +
                "        <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n" +
                "            <ul class=\"navbar-nav\">\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link active\" aria-current=\"page\" href=\"home-page.jsp\">Home</a>\n" +
                "                </li>\n" + userListNavItem +
                "                    <a class=\"nav-link\" href=\"booklist\">Book List</a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link\" href=\"myprofile\" tabindex=\"-1\">My Profile</a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link\" href=\"logout\" tabindex=\"-1\">Logout</a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</nav>";
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.write(navBar);
    }
}
