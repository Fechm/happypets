package com.thedevelopers.happypets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pet")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet",nullable = false,unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="propietario_email",referencedColumnName = "id_email",nullable = false,insertable = false,updatable = false)
    private Person duenio;
    @NotEmpty
    @Column(name = "nombre_pet")
    private String nombre;

    @Email
    @Column(name = "adoptante_email")
    private String adoptante_email;

    @Email
    @Column(name = "propietario_email")
    private String propietario_email;

    @NotNull
    @Column(name = "fechaDeNacimiento")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDeNacimiento;
    @NotEmpty
    @Column(name = "especies")
    private String especies;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet",referencedColumnName = "id_pet")
    private List<Picture> fotos = new ArrayList<>();


    public Pet() {
    }
    @JsonIgnore
    public Person getDuenio() {
        return duenio;
    }

    public void setDuenio(Person duenio) {
        this.duenio = duenio;
    }
    @JsonIgnore
    public List<Picture> getFotos() {
        return fotos;
    }

    public void setFotos(List<Picture> fotos) {
        this.fotos = fotos;
    }

    public String getEspecies() {
        return especies;
    }

    public void setEspecies(String especies) {
        this.especies = especies;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getPropietario_email() {
        return propietario_email;
    }

    public void setPropietario_email(String propietario_email) {
        this.propietario_email = propietario_email;
    }

    public Long getId() {
        return id;
    }

    public String getAdoptante_email() {
        return adoptante_email;
    }

    public void setAdoptante_email(String adoptante_email) {
        this.adoptante_email = adoptante_email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", adoptante_email='" + adoptante_email + '\'' +
                ", propietario_email='" + propietario_email + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", especies='" + especies + '\'' +
                '}';
    }

    private static final long serialVersionUID = 1L;
}
