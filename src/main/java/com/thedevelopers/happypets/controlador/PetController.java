package com.thedevelopers.happypets.controlador;

import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.servicios.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/pets")
public class PetController {
    @Autowired
    private IPetService petService;

    @GetMapping(value = "/listar",produces = {"application/json"})
    public @ResponseBody List<Pet> listar(){
        List<Pet> pets = petService.buscarTodos();
        return pets;
    }
    /*@GetMapping(value = "/form",produces = {""})
    public void IngresarDatos(){

    }*/
}
