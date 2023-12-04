package com.tereshkevich.courseProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Musician")
public class Musician {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 40, message = "Название должно быть от 2 до 40 символов длиной")
    @Column(name = "name")
    private String name;

    @Size(min = 2, max = 90, message = "Список участников должен быть от 2 до 90 символов длиной")
    @Column(name = "members")
    private String members;

    @OneToMany(mappedBy = "name")
    private List<Product> products;

    public Musician() {}

    public Musician(String name, String members) {
        this.name = name;
        this.members = members;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musician musician = (Musician) o;
        return id == musician.id && Objects.equals(name, musician.name) && Objects.equals(members, musician.members) && Objects.equals(products, musician.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, members, products);
    }
}
