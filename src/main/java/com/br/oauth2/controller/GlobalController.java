package com.br.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
