package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Person;
import com.thedevelopers.happypets.model.Pet;

import java.util.List;

public interface IPersonService {

    List<Person> buscarTodos();

    void registrar(Person person);

    Person buscarPersonaPorId(String id);

    void borrarPersonaPorId(String id);

    List<Pet> buscarTodasMascotas();
    
    void listarEnOrden(List<Person> persons);
    
    void save(Person person);

}
