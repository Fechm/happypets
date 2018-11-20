package com.thedevelopers.happypets.controlador;

import com.thedevelopers.happypets.model.User;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class DefaultController {
    @GetMapping(value = "/")
    public String index(Model model, User user){
        return "index";
    }
    @GetMapping(value = "/cerrarSesion")
    public String cerrarSesion(Map<String, Object> model){
        return "logout";
    }      
   
}
