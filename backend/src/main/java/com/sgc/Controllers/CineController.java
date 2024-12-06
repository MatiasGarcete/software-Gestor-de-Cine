package com.sgc.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CineController {

    @GetMapping("/")
    public String home() {
        return "index.html"; // Redirige a static/index.html
    }

    @GetMapping("/login")
    public String login() {
        return "seccion/login.html"; // Redirige a static/login.html
    }

    @GetMapping("/gestionpelicula")
    public String gestionPeliculaMod() {
        return "seccion/pelicula.html"; // Redirige a static/login.html
    }

    @GetMapping("/gestionfuncion")
    public String gestionFuncionMod() {
        return "seccion/funciones.html"; // Redirige a static/login.html
    }
}
