package services;

import org.junit.Assert;
import org.junit.Test;

public class AithenticationServiceTest {

    @Test
    public void testRequestAuthenticationWhenUsernameAndPassAreCorrectThenTrue(){
        AuthenticationService obj = new AuthenticationService();
        String username = "Admin";
        String password = "Admin";
        Assert.assertTrue("The result is not as expected", obj.requestAuthentication(username, password));
    }

    @Test
    public void testRequestAuthenticationWhenPassAreIsNotCorrectThenFalse(){
        AuthenticationService obj = new AuthenticationService();
        String username = "Admin";
        String password = "Ivan";
        Assert.assertFalse("The result is not as expected", obj.requestAuthentication(username, password));
    }

    @Test
    public void testRequestAuthenticationWhenUsernameIsNotCorrectThenFalse(){
        AuthenticationService obj = new AuthenticationService();
        String username = "Teo";
        String password = "Admin";
        Assert.assertFalse("The result is not as expected", obj.requestAuthentication(username, password));
    }

    @Test
    public void testRequestAuthenticationWhenUsernameOrPasswordAreNullThenFalse(){
        AuthenticationService obj = new AuthenticationService();
        String password = "Admin";
        Assert.assertFalse("The result is not as expected", obj.requestAuthentication(null, password));
    }
}
