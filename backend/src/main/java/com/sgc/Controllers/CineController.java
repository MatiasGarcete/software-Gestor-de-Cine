package com.sgc.Controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CineController {
    @GetMapping("/")
    public String prueba() {
        return "Probando Spring...";
    }
    
}
