package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Pet;

import java.util.List;

public interface IPetService  {

    List<Pet> buscarTodos();

    void guardar(Pet pet);

    Pet buscarPetPorId(Long id);

    void borrarPetPorId(Long id);
}
