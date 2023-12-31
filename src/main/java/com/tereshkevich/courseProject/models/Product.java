package com.tereshkevich.courseProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;
    public Product() {}

    public Product(String name, String type, String genre, double price, int year, String description, List<Comment> comments) {
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.price = price;
        this.year = year;
        this.description = description;
        this.comments = comments;
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

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(price, product.price) == 0 && year == product.year && Objects.equals(name, product.name) && Objects.equals(type, product.type) && Objects.equals(genre, product.genre) && Objects.equals(description, product.description) && Objects.equals(musician, product.musician) && Objects.equals(orders, product.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, genre, price, year, description, musician, orders);
    }
}
