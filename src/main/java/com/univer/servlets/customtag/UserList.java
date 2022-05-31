package com.univer.servlets.customtag;

import com.univer.servlets.entity.Book;
import com.univer.servlets.entity.UserModelForUserList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

import static com.univer.servlets.constants.Constants.ALL_USERS_ATTRIBUTE;

public class UserList extends SimpleTagSupport {

    public void doTag() throws IOException {
        StringBuilder userListHtml = new StringBuilder("<table class=\"table\">\n" +
                "        <thead class=\"table-dark\">\n" +
                "        <tr>\n" +
                "            <th scope=\"col\">Login</th>\n" +
                "            <th scope=\"col\">Email</th>\n" +
                "            <th scope=\"col\">First Name</th>\n" +
                "            <th scope=\"col\">Last Name</th>\n" +
                "            <th scope=\"col\">Age</th>\n" +
                "            <th scope=\"col\">Role</th>\n" +
                "            <th scope=\"col\">Books</th>\n" +
                "            <th scope=\"col\">Action</th>\n" +
                "        </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n");
        List<UserModelForUserList> users =
                (List<UserModelForUserList>) getJspContext()
                        .getAttribute(ALL_USERS_ATTRIBUTE, PageContext.SESSION_SCOPE);
        for (UserModelForUserList user : users) {
            userListHtml.append("<tr><td>").append(user.getLogin()).append("</td>\n").append("                <td>").append(user.getEmail()).append("</td>\n").append("                <td>").append(user.getFirstName()).append("</td>\n").append("                <td>").append(user.getLastName()).append("</td>\n").append("                <td>").append(user.getAge()).append("</td>\n").append("                <td>").append(user.getRole().getName()).append("</td>\n").append("                <td>\n").append("                    <ul>\n");
            List<Book> books = user.getBooks();
            for (Book book : books) {
                userListHtml.append("<li>\n").append(book.getAuthor()).append(", ").append(book.getAuthor()).append(", price: ").append(book.getPrice()).append(", genre: ").append(book.getGenre().getName()).append("\n").append("</li>\n");
            }
            userListHtml.append("</ul></td><td><a type=\"button\" href=\"edituser?id=").append(user.getId()).append("\" class=\"btn btn-primary\">Edit</a>\n").append("    <button type=\"button\" class=\"btn btn-danger\" onclick=\"onBeforeDeleteUser(").append(user.getId()).append(")\">Delete</button>\n").append("</td>\n").append("</tr>\n");
        }
        userListHtml.append("</tbody>\n" + "</table>");
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.write(userListHtml.toString());
        jspWriter.close();
    }
}
