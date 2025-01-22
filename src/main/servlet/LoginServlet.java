package main.servlet;

import main.utils.LoggerUtil;
import main.utils.UserDatabase;
import main.constants.Constants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (UserDatabase.isValidUser(username, password)) {
            String role = UserDatabase.getUserRole(username);

            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_USER, username);
            session.setAttribute(Constants.SESSION_ROLE, role);

            LoggerUtil.logUserLogin(username, role);

            response.sendRedirect("/home");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login.html");
    }
}
