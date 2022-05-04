package com.example.springsecurityjdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Default username for spring boot starter security is "user" and password is generated in the console
 * /logout is the pre-defined api in spring security for logging out a user
 */
@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String home(){
        return "<h1>Welcome to Spring Boot Security HOME</h1>";
    }

    @GetMapping(value = "/user")
    public String user(){
        return "<h1>Welcome USER</h1>";
    }

    @GetMapping(value = "/admin")
    public String admin(){
        return "<h1>Welcome ADMIN</h1>";
    }
}
