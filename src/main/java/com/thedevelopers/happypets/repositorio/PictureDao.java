package com.thedevelopers.happypets.repositorio;

import com.thedevelopers.happypets.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureDao extends JpaRepository<Picture,Long> {
}
