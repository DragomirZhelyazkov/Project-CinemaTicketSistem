package services;

import data.UserRepository;
import data.models.Admin;
import data.models.User;

public class AuthenticationService {
    private UserRepository userRepository;
    private User loggedUser;

    public AuthenticationService() {
        this.userRepository = UserRepository.getInstance();
    }

    
    public boolean requestAuthentication(String username, String password) {
        if(isValid(username) && isValid(password)) {
            User user = userRepository.getUserByUsername(username);
            if(user != null && user.getPassword().equals(password)) {
                loggedUser = user;
                return true;
            }
        }

        return false;
    }

    public boolean isUserLogged() {
        return loggedUser != null;
    }

    public void logout() {
        this.loggedUser = null;
    }

    public boolean isUserAdmin() {
        return isUserLogged() && loggedUser instanceof Admin;
    }

    private boolean isValid(String text) {
        return text != null && !text.isEmpty();
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}

