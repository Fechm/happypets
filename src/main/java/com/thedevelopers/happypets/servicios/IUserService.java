package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.User;

import java.util.List;

public interface IUserService {

    List<User> buscarTodos();

    void registrarUser(User pet);

    User buscarUserPorId(String id);

    void borrarUserPorId(String id);
}
