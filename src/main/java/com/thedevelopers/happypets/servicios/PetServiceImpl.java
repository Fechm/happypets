package com.thedevelopers.happypets.servicios;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.repositorio.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class PetServiceImpl implements IPetService {
    @Autowired
    private PetDao petdao;

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Pet> buscarTodos() {
        return em.createQuery("from Pet").getResultList();
    }

    @Override
    public Page<Pet> buscarTodos(Pageable pageable) {
        return petdao.findAll(pageable);
    }

    @Transactional
    @Override
    public void save(Pet pet) {
        if (pet.getId() != null && pet.getId() > 0) {
            em.merge(pet);
        } else {
            em.persist(pet);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Pet buscarPetPorId(Long id) {
        return em.find(Pet.class, id);
    }

    @Transactional
    @Override
    public void borrarPetPorId(Long id) {
        Pet pet = buscarPetPorId(id);
        em.remove(pet);
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
