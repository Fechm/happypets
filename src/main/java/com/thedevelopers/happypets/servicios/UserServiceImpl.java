package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.User;
import com.thedevelopers.happypets.repositorio.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> buscarTodos() {
        return userDao.findAll();
    }

    @Override
    public void registrarUser(User user) {

        userDao.save(user);

    }

    @Override
    public User buscarUserPorId(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public void borrarUserPorId(String id) {
        User u = buscarUserPorId(id);
        if(u.getEmail().equals(id)){
            userDao.delete(u);
        }
    }

}
