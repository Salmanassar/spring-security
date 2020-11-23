package web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public UserService getUserService() {
        return userService;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping
    public ModelAndView getListUser() {
        List<User> users = this.userService.listUsers();
        return new ModelAndView("templates/tl/list", "users", users);
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String getPageForCreating(@ModelAttribute User user) {
        return "templates/tl/form";
    }
//    @RequestMapping("{id}")
//    public ModelAndView view(@PathVariable("id") User user) {
//        return new ModelAndView("templates/tl/list");
//    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("templates/tl/form", "formErrors", result.getAllErrors());
        }
        try {
            user = userService.createUser(user);
        }catch (RuntimeException ex){
            result.addError(new FieldError("user", "user", ex.getMessage()));
            return new ModelAndView("templates/tl/form", "user", user);
        }
        redirect.addFlashAttribute("globalMessage", "Successfully created a new user");
        return new ModelAndView("redirect:/");
    }


    @RequestMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUser(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            User user = userService.readUser(id.get());
            model.addAttribute("user", user);
        }
        return "templates/tl/form";
    }
}
