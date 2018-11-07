package com.thedevelopers.happypets.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "picture")
public class Picture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long idpet;
    @Column(name = "path_picture")
    private String picture;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet",referencedColumnName = "id_pet")
    private Pet pet;

    public Picture() {
    }

    public Long getIdpet() {
        return idpet;
    }

    public void setIdpet(Long idpet) {
        this.idpet = idpet;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
