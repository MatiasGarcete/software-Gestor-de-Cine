package com.sgc.Controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController //esta clase puede manejar peticiones GET - POST - PUT - DELETE
public class CineController {
    @GetMapping("/") //Indicamos la ruta
    public String prueba() { //El metodo que se "dispara" al ingresar a la ruta
        return "Probando Spring...";
    }
}
