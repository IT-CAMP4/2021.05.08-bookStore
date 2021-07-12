package pl.camp.it.validators;

import org.junit.Assert;
import org.junit.Test;

public class LoginValidatorTest {

    @Test
    public void correctValidateLoginTest() {
        String login = "mateusz";

        boolean result = LoginValidator.validateLogin(login);

        Assert.assertTrue(result);
    }

    @Test
    public void notCorrectValidateLoginTest() {
        String login = "ma";

        boolean result = LoginValidator.validateLogin(login);

        Assert.assertFalse(result);
    }

    @Test
    public void correctValidatePasswordTest() {
        String password = "tajne";

        boolean result = LoginValidator.validatePassword(password);

        Assert.assertTrue(result);
    }

    @Test
    public void notCorrectValidatePasswordTest() {
        String password = "";

        boolean result = LoginValidator.validatePassword(password);

        Assert.assertFalse(result);
    }
}
