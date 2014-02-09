package org.georg.web.controller;

import org.georg.web.impl.model.User;
import org.georg.web.impl.service.UserService;
import org.georg.web.impl.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailUtil mailUtil;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/login_private", method = RequestMethod.GET)
    public String loginPrivate(ModelMap model) {
        return "login_private";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.GET)
    public ModelAndView registerNew() {
        ModelAndView page = new ModelAndView("register");
        page.addObject("user", new User());
        return page;
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.POST)
    public ModelAndView registerNewPost(User user) {
        String code = userService.registerNewUser(user);
        ModelAndView page = new ModelAndView("activation_required");
        page.addObject("code", code);
        mailUtil.sendActivationMail(user, code);
        return page;
    }

    @RequestMapping(value = "/activate_user/{code}", method = RequestMethod.GET)
    public ModelAndView activateNewUser(@PathVariable("code") String code) {
        userService.activateUser(code);
        return new ModelAndView("activated");
    }
}
