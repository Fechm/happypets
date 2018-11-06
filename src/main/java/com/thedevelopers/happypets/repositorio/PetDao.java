package com.thedevelopers.happypets.repositorio;

import com.thedevelopers.happypets.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetDao extends JpaRepository<Pet,Long> {
}
