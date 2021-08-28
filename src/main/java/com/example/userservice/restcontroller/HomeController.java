package com.example.userservice.restcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/home")
    public String getHome(){
        log.info("INDEX");
        return "index";
    }
}
