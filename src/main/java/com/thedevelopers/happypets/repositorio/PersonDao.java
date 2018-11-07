package com.thedevelopers.happypets.repositorio;

import com.thedevelopers.happypets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person,String> {
}
