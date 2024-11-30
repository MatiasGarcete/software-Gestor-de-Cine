package com.sgc.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CineController {
    @RequestMapping("/")
    public String home() {
        return "redirect:/index.html"; // Spring intentará buscar la vista "index"
    }
}
