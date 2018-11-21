package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Person;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.repositorio.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonDao personDao;

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Person> buscarTodos() {
        return em.createQuery("from Person").getResultList();
    }
    @Transactional
    @Override
    public void save(Person person) {
        if (person.getEmail() != null && person.getEmail().length() > 0) {
            em.merge(person);
        } else {
            em.persist(person);
        }
    }    
    
    
    @Override
    public void registrar(Person person) {
        personDao.save(person);
    }

    @Override
    public Person buscarPersonaPorId(String id) {
        return personDao.findById(id).orElse(null);
    }

    @Override
    public void borrarPersonaPorId(String id) {
        Person p = buscarPersonaPorId(id);
        System.out.println("del_method");
        if(p.getEmail().equals(id)){
            personDao.delete(p);
            System.out.println("deleted");
        }
    }
    @Override
    public void listarEnOrden(List<Person> persons) {
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPrimerNombre().compareToIgnoreCase(o2.getPrimerNombre());
            }
        });
    }    
    

    
    /*Metodo por implementar para buscar todas las mascotas de una persona*/
    @Override
    public List<Pet> buscarTodasMascotas() {
        return null;
    }
}
