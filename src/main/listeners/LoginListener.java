package main.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import main.constants.Constants;
import main.utils.LoggerUtil;

@WebListener
public class LoginListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute(Constants.SESSION_USER);

        if (username != null) {
            String role = (String) se.getSession().getAttribute(Constants.SESSION_ROLE);
            LoggerUtil.logUserLogin(username, role); // Логируем вход

            System.out.println("User logged in:"  + username + ", Role:" + role);
        } else {
            System.out.println("The session has been created, but the user has not been found.");
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute(Constants.SESSION_USER);

        if (username != null) {
            System.out.println("The user logged out: " + username);
        } else {
            System.out.println("The session has ended, but the username has not been found.");
        }
    }
}

