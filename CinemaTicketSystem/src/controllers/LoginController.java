package controllers;

import io.ConsoleDevice;
import services.AuthenticationService;

public class LoginController implements Controller {

    private final AuthenticationService authenticationService;
    private final ConsoleDevice console;

    public LoginController(ConsoleDevice console, AuthenticationService authenticationService) {
        this.console = console;
        this.authenticationService = authenticationService;
    }

    @Override
    public void start() {
        login();
        if(authenticationService.isUserAdmin()) {
            NavigationController.getInstance(console).startAdmin();
        } else {
            NavigationController.getInstance(console).startClient();
        }
    }

    private void login() {
        while (!authenticationService.isUserLogged()) {
            console.showLoginMessage();
            String username = console.getUsernameFromUser();
            String password = console.getPasswordFromUser();
            if(!authenticationService.requestAuthentication(username, password)) {
                console.showErrorWrongLoginCredentials();
            }
        }
    }
}

