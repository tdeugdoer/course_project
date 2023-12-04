package com.tereshkevich.courseProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 40, message = "Название должно быть от 2 до 40 символов длиной")
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "genre")
    private String genre;

    @Min(value = 0, message = "Цена должна быть не меньше 0")
    @Max(value = 999999, message = "Цена должна быть не больше 1000000")
    @Column(name = "price")
    private double price;

    @Min(value = 1900, message = "Год не должен быть меньше 1900")
    @Max(value = 2023, message = "Год не должен превышать 2023")
    @Column(name = "year")
    private int year;
    @Size(max = 70, message = "Описание должно быть длиной менее 70 символов")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "musician_id")
    private Musician musician;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;

    public Product() {}

    public Product(String name, String type, String genre, double price, int year, String description) {
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.price = price;
        this.year = year;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Musician getMusician() { return musician; }
    public void setMusician(Musician musician) { this.musician = musician; }
}
