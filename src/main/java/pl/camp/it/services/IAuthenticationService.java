package pl.camp.it.services;

import pl.camp.it.model.User;

public interface IAuthenticationService {
    boolean authenticate(String login, String password);
    void registerUser(User user);
}
