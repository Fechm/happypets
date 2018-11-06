package com.thedevelopers.happypets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name ="person")
public class Person implements Serializable {
    @Id
    @Column(name = "id_email")
    private String email;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_de_cumpleanios")
    private Date fechaDeCumpleaños;

    @Column(name = "run")
    private Integer run;

    @Column(name = "numero_de_telefono")
    private String nroTelefono;

    @Column(name = "calle")
    private String calle;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "numero_de_calle")
    private Integer nroCalle;

    public Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getFechaDeCumpleaños() {
        return fechaDeCumpleaños;
    }

    public void setFechaDeCumpleaños(Date fechaDeCumpleaños) {
        this.fechaDeCumpleaños = fechaDeCumpleaños;
    }

    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
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

    public Integer getNroCalle() {
        return nroCalle;
    }

    public void setNroCalle(Integer nroCalle) {
        this.nroCalle = nroCalle;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", primerNombre='" + primerNombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaDeCumpleaños=" + fechaDeCumpleaños +
                ", run=" + run +
                ", nroTelefono='" + nroTelefono + '\'' +
                ", calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", nroCalle=" + nroCalle +
                '}';
    }
}
