package pl.camp.it.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.configuration.TestConfiguration;
import pl.camp.it.services.IAuthenticationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class AuthenticationServiceTest {

    @Autowired
    IAuthenticationService authenticationService;

    @Test
    public void correctAuthenticationTest() {
        String login = "mateusz";
        String password = "mateusz";

        boolean result = authenticationService.authenticate(login, password);

        Assert.assertTrue(result);
    }

    @Test
    public void incorrectPasswordAuthenticationTest() {
        String login = "mateusz";
        String password = "mateusz2";

        boolean result = this.authenticationService.authenticate(login, password);

        Assert.assertFalse(result);
    }

    @Test
    public void incorrectLoginAuthenticationTest() {
        String login = "mateusz2";
        String password = "mateusz";

        boolean result = this.authenticationService.authenticate(login, password);

        Assert.assertFalse(result);
    }
}
