package data.consts;

import org.junit.Assert;
import org.junit.Test;

public class ClientOptionTest {
    @Test
    public void testConvertToClientOptionWhenParameterIsFiveThenResultIsNull(){
        Assert.assertEquals("The result is not as expected", null, ClientOption.convertToClientOption(5));
    }

    @Test
    public void testConvertToClientOptionWhenParameterIsOneThenResultIsBuyTicket(){
        Assert.assertEquals("The result is not as expected", ClientOption.BUY_TICKET,
                ClientOption.convertToClientOption(1));
    }

    @Test
    public void testConvertToClientOptionWhenParameterIsTwoThenResultIsLogout(){
        Assert.assertEquals("The result is not as expected", ClientOption.LOGOUT,
                ClientOption.convertToClientOption(2));
    }
}
