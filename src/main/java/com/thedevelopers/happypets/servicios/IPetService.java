package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Pet;

import java.util.List;

public interface IPetService  {

    List<Pet> buscarTodos();

    void save(Pet pet);

    Pet buscarPetPorId(Long id);

    void borrarPetPorId(Long id);

    void listarEnOrden(List<Pet> pets);
}
