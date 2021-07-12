package pl.camp.it.session;

import org.junit.Assert;
import org.junit.Test;

public class SessionObjectTest {

    @Test
    public void getInfoTest() {
        SessionObject sessionObject = new SessionObject();
        String info = "Info";

        Assert.assertNull(sessionObject.getInfo());

        sessionObject.setInfo(info);

        String result = sessionObject.getInfo();

        Assert.assertEquals(info, result);

        Assert.assertNull(sessionObject.getInfo());
    }
}
