package data;

import data.models.Admin;
import data.models.Client;
import data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final UserRepository instance = new UserRepository();
    public static UserRepository getInstance() {
        return instance;
    }

    private final List<User> users;

    private UserRepository() {
        users = new ArrayList<>();
        users.add(new Admin("Admin", "Admin"));
        users.add(new Client("Drago", "Drago"));
        users.add(new Client("Iva", "Iva"));
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void createUser(User user) {
        users.add(user);
    }//излишен

    public List<User> getAllUsers() {
        return users;
    }//излишен
}

