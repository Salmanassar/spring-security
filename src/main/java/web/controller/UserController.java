package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public UserService getUserService() {
        return userService;
    }

    @RequestMapping("user")
    public ModelAndView getUser(Principal principal, ModelAndView modelAndView) {
        List<User> users = this.userService.listUsers();
        return new ModelAndView("templates/user/userList", "users", users);
    }

    @PreAuthorize("hasAuthority('users:read')")
    @RequestMapping(path = {"/modify", "/modify/{number}"})
    public String editUser(Model model, @PathVariable("number") Optional<Long> id) {
        if (id.isPresent()) {
            User user = userService.readUser(id.get());
            model.addAttribute("user", user);
        }
        return "templates/user/formUser";
    }

    @PreAuthorize("hasAuthority('users:read')")
    @RequestMapping("{number}")
    public ModelAndView returnUserById(@PathVariable("number") User user) {
        return new ModelAndView("templates/user/formUser");
    }
}
