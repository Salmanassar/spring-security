package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
class RegistrationController {

    private UserService userService;

    @Autowired
    public UserService getUserService() {
        return userService;
    }

    @RequestMapping(value = "signup")
    public ModelAndView registrationForm() {
        return new ModelAndView("templates/registration", "user", new User());
    }

    @RequestMapping(value = "user/register")
    public ModelAndView registerUser(@Valid final User user, final BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("templates/registration", "user", user);
        }
        try {
            userService.createUser(user);
        } catch (Exception e) {
            result.addError(new FieldError("user", "user", e.getMessage()));
            return new ModelAndView("templates/registration", "user", user);
        }
        return new ModelAndView("redirect:/loginPage");
    }

}
