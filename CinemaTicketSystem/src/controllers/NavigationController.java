package controllers;

import io.ConsoleDevice;
import services.AuthenticationService;

public class NavigationController {
    private static NavigationController instance;

    public static NavigationController getInstance(ConsoleDevice console) {
        if(instance == null) instance = new NavigationController(console);

        return instance;
    }

    private final Controller loginController;
    private final Controller adminController;
    private final Controller clientController;

    private NavigationController(ConsoleDevice console) {
        AuthenticationService authenticationService = new AuthenticationService();

        loginController = new LoginController(console, authenticationService);
        adminController = new AdminController(console, authenticationService);
        clientController = new ClientController(console, authenticationService);
    }

    public void startLogin() {
        loginController.start();
    }

    public void startAdmin() {
        adminController.start();
    }

    public void startClient() {
        clientController.start();
    }
}

