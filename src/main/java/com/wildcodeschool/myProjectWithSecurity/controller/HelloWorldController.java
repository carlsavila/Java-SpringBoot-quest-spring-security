package com.wildcodeschool.myProjectWithSecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
        
    @GetMapping("/avengers/assemble")
    public String avengers() {
    	return " Avengers... Assemble. Steve CHAMPION. Accessible uniquement aux champions du SHIELD";
    }
    @GetMapping("/secret-bases")
    public String secretBases() {
    	return "Hello Nick, DIRECTOR, sur secrets bases!!! n'est accessible que par le directeur du SHIELD";
    }
    
}
