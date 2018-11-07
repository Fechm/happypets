package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Person;
import com.thedevelopers.happypets.model.Pet;
import com.thedevelopers.happypets.repositorio.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Person> buscarTodos() {
        return personDao.findAll();
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
        if(p.getEmail().equals(id)){
            personDao.delete(p);
        }
    }
    /*Metodo por implementar para buscar todas las mascotas de una persona*/
    @Override
    public List<Pet> buscarTodasMascotas() {
        return null;
    }
}
