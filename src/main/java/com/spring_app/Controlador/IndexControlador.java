package com.spring_app.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {
    @GetMapping("/")
    private String index(){
        return "Producto/index";
    }


}
