package ru.graduate.work.budget.planning.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebConfig {
    @GetMapping("/")
    public String products() {
        return "home";
    }
}
