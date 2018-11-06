package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Person;

import java.util.List;

public interface IPersonService {

    List<Person> buscarTodos();

    void registrar(Person pet);

    Person buscarPersonaPorId(String id);

    void borrarPersonaPorId(String id);
}
