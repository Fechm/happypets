package com.thedevelopers.happypets.servicios;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.model.Picture;
import com.thedevelopers.happypets.repositorio.PetDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class PetServiceImpl implements IPetService {

    @Autowired
    private PetDao petDao;
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
        if (pet.getId() != null && pet.getId() > 0) {
            em.merge(pet);
        } else {
            em.persist(pet);
        }
    }

    @Override
    public List<Pet> buscarPetsPorDuenio(String id) {
        String query = "from pet where propietario_email=id";
        List<Pet> temp = em.createQuery(query).getResultList();
        for(int i = 0; i<temp.size();i++){
            Long petid = temp.get(i).getId();
            temp.get(i).setFotos(buscarFotosPorId(petid));
        }
        return temp;
    }

    @Override
    public Pet buscarPetPorId(Long id) {
        Pet pet = petDao.findById(id).orElse(null);
        pet.setFotos(buscarFotosPorId(id));
        return pet;
    }

    private List<Picture> buscarFotosPorId(Long id){
        String query = "from picture where id_pet = id";
        return em.createQuery(query).getResultList();
    }
    @Override
    public void borrarPetPorId(Long id) {
        Pet p = petDao.findById(id).orElse(null);
        if(p.getId()==id){
            petDao.delete(p);
        }
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