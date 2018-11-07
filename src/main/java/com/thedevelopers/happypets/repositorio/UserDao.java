package com.thedevelopers.happypets.repositorio;

import com.thedevelopers.happypets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {
}
