package com.thedevelopers.happypets.controlador;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.servicios.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("pet")
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
    public String guardar(@Valid Pet pet, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de mascota");
            return "pform";
        }
        petService.save(pet);
        status.setComplete();
        return "redirect:/pets/listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        return "redirect:/pets/listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String Editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Pet pet = null;
        if (id > 0) {
            pet = petService.buscarPetPorId(id);
        } else {
            return "redirect:/pets/listar";
        }
        model.put("pet", pet);
        model.put("titulo", "Editar Mascota");
        return "pform";
    }

    @RequestMapping(value = "/delete/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {
        Pet pet = null;
        if (id > 0) {
            petService.borrarPetPorId(id);
        }
        return "redirect:/pets/listar";
    }

}
