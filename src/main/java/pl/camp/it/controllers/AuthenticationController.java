package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.model.User;
import pl.camp.it.services.IAuthenticationService;
import pl.camp.it.session.SessionObject;
import pl.camp.it.validators.LoginValidator;

import javax.annotation.Resource;

@Controller
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("info", this.sessionObject.getInfo());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        if(!LoginValidator.validateLogin(login) || !LoginValidator.validatePassword(password)) {
            System.out.println("Nie przeszła walidacja logowania!!");
            System.out.println("Dane: " + login + ", hasło: " + password);
            this.sessionObject.setInfo("Logowanie nieudane !!");
            return "redirect:/login";
        }

        if(authenticationService.authenticate(login, password)) {
            return "redirect:/main";
        } else {
            this.sessionObject.setInfo("Logowanie nieudane !!");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.logoutUser();
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user) {
        this.authenticationService.registerUser(user);

        return "redirect:/login";
    }
}
