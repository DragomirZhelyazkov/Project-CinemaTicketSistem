package controllers;

import org.junit.Test;
import services.AuthenticationService;

public class ClientControllerTest {
    @Test(expected = NullPointerException.class)
    public void testStartWhenIoDeviceIsNullThenAppCrash(){
        AuthenticationService service = new AuthenticationService();
        ClientController controller = new ClientController(null, service);
        controller.start();
    }
}
