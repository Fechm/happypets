package com.thedevelopers.happypets.controlador;

import com.thedevelopers.happypets.model.Person;
import com.thedevelopers.happypets.servicios.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
