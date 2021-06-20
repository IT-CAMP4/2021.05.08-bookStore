package pl.camp.it.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.dao.impl.UserDAO;
import pl.camp.it.model.User;
import pl.camp.it.services.IAuthenticationService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    public boolean authenticate(String login, String password) {
        User user = this.userDAO.getUserByLogin(login);

        if(user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
            sessionObject.setUser(user);
            return true;
        }

        return false;
    }

    public void registerUser(User user) {
        user.setRole(User.Role.USER);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userDAO.addUser(user);
    }
}
