package com.thedevelopers.happypets.controlador;

import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.servicios.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/pets")
public class PetController {
    @Autowired
    private IPetService petService;

    @GetMapping(value = "/listar")
    public String listarMascotas(Model model, Pet pet) {
        List<Pet> pets = petService.buscarTodos();
        model.addAttribute("titulo", "Listado de mascotas");
        model.addAttribute("pets", pets);
        return "plist";
    }

    @GetMapping(value = "/listarO")
    public String listarMascotasOrdenadasPorNombre(Model model, Pet pet){
        model.addAttribute("titulo", "Listado de mascotas ordenadas por nombre");
        List<Pet> temp = petService.buscarTodos();
        petService.listarEnOrden(temp);
        model.addAttribute("pets",temp);
        return "plist";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Pet pet = new Pet();
        model.put("pet", pet);
        model.put("titulo", "Formulario de mascota");
        return "pform";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Pet pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de mascota");
            return "pform";
        }
        petService.save(pet);
        return "redirect:listar";
    }

}
