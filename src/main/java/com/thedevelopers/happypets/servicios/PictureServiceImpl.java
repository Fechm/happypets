package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Picture;
import com.thedevelopers.happypets.repositorio.PictureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements IPictureService {
    @Autowired
    private PictureDao pictureDao;
    @Override
    public List<Picture> buscarTodas() {
        return pictureDao.findAll();
    }

    @Override
    public List<Picture> buscarPorId(Long idpet) {
        return null;
    }

    @Override
    public void Ingresar(Picture picture) {
        pictureDao.save(picture);
    }

    @Override
    public void borrarPorId(Long idpet) {
        pictureDao.deleteById(idpet);
    }
}
