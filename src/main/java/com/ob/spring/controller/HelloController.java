package com.ob.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class HelloController {

    @GetMapping("/saludar")
    public String saludar(){
        return "Hola, saludos desde HelloController!!";
    }

}
