package pl.camp.it.dao.impl;

import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;

public class HibernateUserDAOStub implements IUserDAO {
    @Override
    public User getUserByLogin(String login) {
        if(login.equals("mateusz")) {
            User user = new User();
            user.setLogin("mateusz");
            user.setRole(User.Role.USER);
            user.setId(1);
            user.setName("Mateusz");
            user.setSurname("Bereda");
            user.setPassword("617f41f48d1d4f9c787aed6b0ebc6f7d");
            return user;
        } else {
            return null;
        }
    }

    @Override
    public void addUser(User user) {

    }
}
