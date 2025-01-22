package main.servlet;

import main.constants.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute(Constants.SESSION_USER) != null) {
            String username = (String) session.getAttribute(Constants.SESSION_USER);
            String role = (String) session.getAttribute(Constants.SESSION_ROLE);

            response.getWriter().write("Welcome, " + username + ". Your role: " + role);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access is denied. Please log in.");
        }
    }
}
