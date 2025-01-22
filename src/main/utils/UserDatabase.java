package main.utils;

import main.constants.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final Map<String, String[]> users = new HashMap<>();

    static {
        loadUsersFromFile();
    }

    private static void loadUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.USER_DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    users.put(parts[0], new String[]{parts[2], parts[1]});
                }
            }
        } catch (IOException e) {
            System.err.println("Error uploading user data: " + e.getMessage());
        }
    }

    public static String getUserRole(String username) {
        return users.get(username)[1];
    }

    public static boolean isValidUser(String username, String password) {
        String[] user = users.get(username);
        return user != null && user[0].equals(password);
    }
}
