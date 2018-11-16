package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPetService  {

    List<Pet> buscarTodos();

    Page<Pet> buscarTodos(Pageable pageable);

    void save(Pet pet);

    Pet buscarPetPorId(Long id);

    void borrarPetPorId(Long id);

    void listarEnOrden(List<Pet> pets);
}
