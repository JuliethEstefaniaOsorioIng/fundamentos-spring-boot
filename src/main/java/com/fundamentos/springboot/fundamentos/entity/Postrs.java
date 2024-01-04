package com.fundamentos.springboot.fundamentos.entity;

import jakarta.persistence.*;

@Entity
@Table(name="postrs")
public class Postrs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_postrs", nullable=false, unique=true)
    private Long id;

    @Column(name="description", length = 255)
    private String description;

    @ManyToOne
    private Users users;

    public Postrs() {
    }

    public Postrs(String description, Users users) {
        this.description = description;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users user) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + users +
                '}';
    }
}
