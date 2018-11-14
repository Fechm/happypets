package com.thedevelopers.happypets.servicios;
import com.thedevelopers.happypets.model.Pet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class PetServiceImpl implements IPetService {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Pet> buscarTodos() {
        return em.createQuery("from Pet").getResultList();
    }

    @Transactional
    @Override
    public void save(Pet pet) {
        em.persist(pet);
    }

    @Override
    public Pet buscarPetPorId(Long id) {
        return null;
    }

    @Override
    public void borrarPetPorId(Long id) {

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
