package com.thedevelopers.happypets.controlador;

import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.servicios.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public @ResponseBody List<Pet> listarMascotas(){
        List<Pet> pets = petService.buscarTodos();
        return pets;
    }
    @GetMapping(value="/formO")
    public String listarMascotasOrdenadasPorNombre(Model model, Pet pet){
        model.addAttribute("pet",new Pet());
        List<Pet> temp = petService.buscarTodos();
        petService.listarEnOrden(temp);
        model.addAttribute("pets",temp);
        return "pform";
    }

}
