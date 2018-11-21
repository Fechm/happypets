package com.thedevelopers.happypets.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "User")
public class User implements Serializable {
    @Email
    @Id
    @Column(name = "user_email",nullable = false,unique = true,length = 50)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email")
    private Person userperson;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getUserpersonPerson() {
        return userperson;
    }

    public void setUserpersonPerson(Person person) {
        this.userperson = person;
    }
}
