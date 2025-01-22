package main.utils;

import main.constants.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerUtil {
    public static void logUserLogin(String username, String role) {
        try (FileWriter writer = new FileWriter(Constants.LOG_FILE, true)) {
            writer.write(LocalDateTime.now() + " - User: " + username + ", Role: " + role + "\n");
        } catch (IOException e) {
            System.err.println("Log entry error: " + e.getMessage());
        }
    }
}
