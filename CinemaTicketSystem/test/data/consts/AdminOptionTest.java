package data.consts;

import org.junit.Assert;
import org.junit.Test;

public class AdminOptionTest {

    @Test
    public void testConvertToAdminOptionWhenParameterIsFiveThenResultIsNull(){
        Assert.assertEquals("The result is not as expected", null, AdminOption.convertToAdminOption(5));
    }

    @Test
    public void testConvertToAdminOptionWhenParameterIsOneThenResultIsAddMovie(){
        Assert.assertEquals("The result is not as expected", AdminOption.ADD_MOVIE,
                AdminOption.convertToAdminOption(1));
    }

    @Test
    public void testConvertToAdminOptionWhenParameterIsTwoThenResultIsLogout(){
        Assert.assertEquals("The result is not as expected", AdminOption.LOGOUT,
                AdminOption.convertToAdminOption(2));
    }

}
