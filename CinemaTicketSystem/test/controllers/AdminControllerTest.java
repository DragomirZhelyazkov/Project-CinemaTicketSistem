package controllers;

import org.junit.Test;
import services.AuthenticationService;

public class AdminControllerTest {

    @Test(expected = NullPointerException.class)
    public void testStartWhenIoDeviceIsNullThenAppCrash(){
        AuthenticationService service = new AuthenticationService();
        AdminController controller = new AdminController(null, service);
        controller.start();
    }
}
