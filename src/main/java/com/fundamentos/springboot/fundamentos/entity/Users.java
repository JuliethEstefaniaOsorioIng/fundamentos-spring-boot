package com.fundamentos.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_users", nullable=false, unique=true)
    private Long id;

    @Column(name="name", length = 50)
    private String name;

    @Column(name="email", length = 50, unique = true)
    private String email;

    @Column(name="birthday")
    private LocalDate birthday;

    @OneToMany(mappedBy = "users", cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Postrs> postrs = new ArrayList<>();

    public Users() {
    }

    public Users(Long id, String name, String email, LocalDate birthday) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public Users(String name, String email, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", post=" + postrs +
                '}';
    }
}
