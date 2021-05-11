package pl.camp.it.validators;

public class LoginValidator {
    public static boolean validateLogin(String login) {
        return !(login.length() < 3);
    }

    public static boolean validatePassword(String password) {
        return !(password.length() < 3);
    }
}
