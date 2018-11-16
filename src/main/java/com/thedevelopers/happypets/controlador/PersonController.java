package com.thedevelopers.happypets.controlador;

import com.thedevelopers.happypets.model.Person;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.servicios.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/persons")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping(value = "/listar",produces = {"application/json"})
    public @ResponseBody List<Person> listar(){
        List<Person> persons = personService.buscarTodos();
        return persons;
    }
    @GetMapping(value = "/listarO")
    public String listarPersonasOrdenadasPorNombre(Model model, Person person){
        model.addAttribute("titulo", "Listado de personas ordenadas por nombre");
        List<Person> temp = personService.buscarTodos();
        personService.listarEnOrden(temp);
        model.addAttribute("persons",temp);
        return "personList";
    }    
    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Person person = new Person();
        model.put("person", person);
        model.put("titulo", "Formulario de persona");
        return "personForm";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Person person, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de personas");
            return "personForm";
        }
        personService.save(person);
        status.setComplete();
        return "redirect:/persons/listarO";
    }

    @RequestMapping(value = "/form/{email}")
    public String editar(@PathVariable(value = "email") String email, Map<String, Object> model) {
        Person person = null;
        if (email.length() > 0) {
            person = personService.buscarPersonaPorId(email);
        } else {
            return "redirect:/person/listarO";
        }
        model.put("person", person);
        model.put("titulo", "Editar Persona");
        return "personForm";
    }
}
