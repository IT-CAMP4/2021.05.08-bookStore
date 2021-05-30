package pl.camp.it.dao;

import pl.camp.it.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    void addUser(User user);
}
