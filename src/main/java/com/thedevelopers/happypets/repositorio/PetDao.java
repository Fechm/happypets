package com.thedevelopers.happypets.repositorio;

import com.thedevelopers.happypets.model.Pet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetDao extends PagingAndSortingRepository<Pet, Long> {
}
