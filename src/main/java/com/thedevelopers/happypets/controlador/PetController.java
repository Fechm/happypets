package com.thedevelopers.happypets.controlador;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.model.Picture;
import com.thedevelopers.happypets.servicios.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("pet")
@RequestMapping(value = "/pets")
public class PetController {
    @Autowired
    private IPetService petService;

    @GetMapping(value = "/listar")
    public String listarMascotas(Model model) {
        model.addAttribute("titulo", "Listado de mascotas");
        List<Pet> temp = petService.buscarTodos();
        model.addAttribute("pets", temp);
        return "plist";
    }

    @GetMapping(value = "/listarO")
    public String listarMascotasOrdenadasPorNombre(Model model, Pet pet){
        model.addAttribute("titulo", "Listado de mascotas ordenadas por nombre");
        List<Pet> temp = petService.buscarTodos();

        petService.listarEnOrden(temp);
        for(int i=0; i<temp.size();i++) {
	        if(temp.get(i).getAdoptante_email().length()>0) {
	        	temp.remove(i);
	        }
        }
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
    public String guardar(@Valid Pet pet, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de mascota");
            return "pform";
        }
        if (!foto.isEmpty()) {
            Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
            String rootPath = directorioRecursos.toFile().getAbsolutePath();
            try {

                byte[] bytes = foto.getBytes();
                Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                List<Picture> pics = new ArrayList<Picture>();
                Picture p = new Picture();
                p.setIdpet(pet.getId());
                p.setPicture(foto.getOriginalFilename());
                pics.add(p);
                pet.setFotos(pics);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        petService.save(pet);
        status.setComplete();
        return "redirect:/pets/listarO";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Pet pet = null;
        if (id > 0) {
            pet = petService.buscarPetPorId(id);
        } else {
            return "redirect:/pets/listarO";
        }
        model.put("pet", pet);
        model.put("titulo", "Editar Mascota");
        return "pform";
    }

    @RequestMapping(value = "/delete/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            petService.borrarPetPorId(id);
        }
        return "redirect:/pets/myPets";
    }
    
    @RequestMapping(value = "/myPets/{email}")
    public String BuscarPorPropietario(Model model, Pet pet, @PathVariable(value = "email") String email) {
        model.addAttribute("titulo", "Listado de mascotas ordenadas por nombre");
        List<Pet> temp = petService.buscarTodos();
        List<Pet> aux = new ArrayList<>();
        
        for(int k=0; k <temp.size();k++) {
	        if(temp.get(k).getPropietario_email().equals(email)) {
	        	aux.add(temp.get(k));	        	
	        }
        }
        temp.clear();
        model.addAttribute("pets",aux);
        return "myplist";
    }
    @RequestMapping(value = "/myPets")
    public String MyPets(Model model) {
    	model.addAttribute("titulo", "Listado de mascotas ordenadas por nombre");
        return "myplist";
    }
    
}