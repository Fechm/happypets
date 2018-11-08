package com.thedevelopers.happypets.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="person")
public class Person implements Serializable {
    @Id
    @Column(name = "id_email",nullable = false,unique = true,length = 50)
    private String email;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_email",referencedColumnName = "user_email")
    private User user;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_de_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;

    @Column(name = "run")
    private Long run;

    @Column(name = "numero_de_telefono")
    private String nroTelefono;

    @Column(name = "calle")
    private String calle;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "numero_de_calle")
    private Long nroCalle;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_email",referencedColumnName = "id_email")
    private List<Pet> mascotas = new ArrayList<>();

    public Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonIgnore
    public List<Pet> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Pet> mascotas) {
        this.mascotas = mascotas;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Long getRun() {
        return run;
    }

    public void setRun(Long run) {
        this.run = run;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getNroCalle() {
        return nroCalle;
    }

    public void setNroCalle(Long nroCalle) {
        this.nroCalle = nroCalle;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", primer Nombre='" + primerNombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fecha de nacimiento=" + fechaDeNacimiento +
                ", run=" + run +
                ", numero de telefono='" + nroTelefono + '\'' +
                ", calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", numero de calle=" + nroCalle +
                '}';
    }
}
