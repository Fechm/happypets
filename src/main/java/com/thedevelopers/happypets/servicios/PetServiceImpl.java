package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.repositorio.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PetServiceImpl implements IPetService {

    @Autowired
    private PetDao petdao;

    @Override
    public List<Pet> buscarTodos() {
        return petdao.findAll();
    }

    @Override
    public void guardar(Pet pet) {
        petdao.save(pet);
    }

    @Override
    public Pet buscarPetPorId(Long id) {
        return petdao.findById(id).orElse(null);
    }

    @Override
    public void borrarPetPorId(Long id) {
        petdao.deleteById(id);
    }

    @Override
    public void listarEnOrden(List<Pet> pets) {
        Collections.sort(pets, new Comparator<Pet>() {
            @Override
            public int compare(Pet o1, Pet o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });
    }



}
