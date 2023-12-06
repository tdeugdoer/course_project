package com.tereshkevich.courseProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 100, message = "Текст должен быть от 2 до 100 символов длиной")
    @Column(name = "text")
    private String text;

    @Size(min = 2, max = 20, message = "Логин должен быть от 2 до 100 символов длиной")
    @Column(name = "login")
    private String person;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Comment() {}

    public Comment(String text, String person, Product product) {
        this.text = text;
        this.person = person;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
