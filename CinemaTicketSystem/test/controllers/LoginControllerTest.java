package controllers;

import org.junit.Test;
import services.AuthenticationService;

public class LoginControllerTest {

    @Test(expected = NullPointerException.class)
    public void testStartWhenIoDeviceIsNullThenAppCrash(){
        AuthenticationService service = new AuthenticationService();
        LoginController controller = new LoginController(null, service);
        controller.start();
    }
}
