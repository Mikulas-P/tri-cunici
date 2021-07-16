package cz.ppp.music.controller;

import cz.ppp.music.entity.User;
import cz.ppp.music.service.security.SecurityService;
import cz.ppp.music.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
@SuppressWarnings("unused")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping("/login")
    public String login(Model model, String error, String logout){
        if(error != null)
            model.addAttribute("error", "Wrong username or email");
        if(logout != null)
            model.addAttribute("message", "Logout successful");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("newUser") User userForm, BindingResult bindingResult) {
        if(userService.checkIfUserExists(userForm.getUsername()))
            bindingResult.rejectValue("username","","This user is already exist!");
        if(bindingResult.hasErrors())
            return "registration";
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
        return "redirect:/index";
    }

}
