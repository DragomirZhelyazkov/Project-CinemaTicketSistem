package controllers;

import org.junit.Test;

public class NavigationControllerTest {

    @Test(expected = NullPointerException.class)
    public void testStartLoginWhenIoDeviceIsNullThenAppCrash(){
        NavigationController.getInstance(null).startLogin();
    }

    @Test(expected = NullPointerException.class)
    public void testStartAdminWhenIoDeviceIsNullThenAppCrash(){
        NavigationController.getInstance(null).startAdmin();
    }

    @Test(expected = NullPointerException.class)
    public void testStartClientWhenIoDeviceIsNullThenAppCrash(){
        NavigationController.getInstance(null).startClient();
    }
}
