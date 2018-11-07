package com.thedevelopers.happypets.servicios;

import com.thedevelopers.happypets.model.Picture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPictureService {
    List<Picture> buscarTodas();

    List<Picture> buscarPorId(Long idpet);

    void Ingresar(Picture picture);

    void borrarPorId(Long idpet);
}
