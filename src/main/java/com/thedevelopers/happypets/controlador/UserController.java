package com.thedevelopers.happypets.controlador;

import com.thedevelopers.happypets.model.User;
import com.thedevelopers.happypets.servicios.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping(value = "/listar",produces = {"application/json"})
    public @ResponseBody List<User> listar(){
        List<User> listadeusuarios =userService.buscarTodos();
        return listadeusuarios;
    }
    @GetMapping(value = "/form")
    public String index(Model model, User user){
        model.addAttribute("user",new User());
        model.addAttribute("users",userService.buscarTodos());
        return "uForm";
    }
}
