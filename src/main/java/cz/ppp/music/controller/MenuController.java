package cz.ppp.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/contact")
    public String toContact (){
        return "contact";
    }

}
